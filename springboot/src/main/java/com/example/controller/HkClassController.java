package com.example.controller;

import com.example.common.config.TokenUtils;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.*;
import com.example.service.*;
import com.example.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.*;

import java.util.List;

/**
*  描述：班级管理相关接口
*/
@RestController
@RequestMapping("/hkClass")
public class HkClassController {

    @Resource
    HkClassService hkClassService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody HkClass hkClass) {

        hkClassService.add(hkClass);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        hkClassService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        hkClassService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody HkClass hkClass) {

        hkClassService.updateById(hkClass);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        HkClass hkClass = hkClassService.selectById(id);
        return Result.success(hkClass);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(HkClass hkClass) {
        List<HkClass> list = hkClassService.selectAll(hkClass);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            HkClass hkClass,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<HkClass> pageInfo = hkClassService.selectPage(hkClass, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			hkClassService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<HkClass> all = hkClassService.selectAll(new HkClass());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("班级ID", null);
			row.put("班级", null);
			row.put("毕业时间", null);
			row.put("是否毕业", null);
			row.put("辅导员", null);
			list.add(row);
		} else {
			for (HkClass hkClass : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("班级ID", hkClass.getClassiD());
				row.put("班级", hkClass.getClassName());
				row.put("毕业时间", hkClass.getGraduationTime());
				row.put("是否毕业", hkClass.getGraduation());
				row.put("辅导员", hkClass.getStudentTeacher());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=hkClassInfoExcel.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		writer.close();
		IoUtil.close(System.out);
	}

	/**
	 * 描述：通过excel批量导入
	 */
	@PostMapping("/upload")
	public Result upload(MultipartFile file) throws IOException {
		List<HkClass> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(HkClass.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (HkClass info : infoList) {
				try {
					hkClassService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
