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
*  描述：学生管理相关接口
*/
@RestController
@RequestMapping("/hkStudent")
public class HkStudentController {

    @Resource
    HkStudentService hkStudentService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody HkStudent hkStudent) {

        hkStudentService.add(hkStudent);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        hkStudentService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        hkStudentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody HkStudent hkStudent) {

        hkStudentService.updateById(hkStudent);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        HkStudent hkStudent = hkStudentService.selectById(id);
        return Result.success(hkStudent);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(HkStudent hkStudent) {
        List<HkStudent> list = hkStudentService.selectAll(hkStudent);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            HkStudent hkStudent,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<HkStudent> pageInfo = hkStudentService.selectPage(hkStudent, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			hkStudentService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<HkStudent> all = hkStudentService.selectAll(new HkStudent());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("学号", null);
			row.put("学生姓名", null);
			row.put("生日", null);
			row.put("免打扰", null);
			row.put("辅导员", null);
			row.put("班级", null);
			row.put("电话号码", null);
			list.add(row);
		} else {
			for (HkStudent hkStudent : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("学号", hkStudent.getStudentid());
				row.put("学生姓名", hkStudent.getStudentname());
				row.put("生日", hkStudent.getBirthday());
				row.put("免打扰", hkStudent.getNotDisturb());
				row.put("辅导员", hkStudent.getStudentTeacher());
				row.put("班级", hkStudent.getStudentClass());
				row.put("电话号码", hkStudent.getTelephoneNumber());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=hkStudentInfoExcel.xlsx");
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
		List<HkStudent> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(HkStudent.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (HkStudent info : infoList) {
				try {
					hkStudentService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
