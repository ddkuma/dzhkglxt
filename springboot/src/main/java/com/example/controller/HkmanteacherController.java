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
*  描述：管理老师相关接口
*/
@RestController
@RequestMapping("/hkmanteacher")
public class HkmanteacherController {

    @Resource
    HkmanteacherService hkmanteacherService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Hkmanteacher hkmanteacher) {

        hkmanteacherService.add(hkmanteacher);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        hkmanteacherService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        hkmanteacherService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Hkmanteacher hkmanteacher) {

        hkmanteacherService.updateById(hkmanteacher);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Hkmanteacher hkmanteacher = hkmanteacherService.selectById(id);
        return Result.success(hkmanteacher);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Hkmanteacher hkmanteacher) {
        List<Hkmanteacher> list = hkmanteacherService.selectAll(hkmanteacher);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Hkmanteacher hkmanteacher,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Hkmanteacher> pageInfo = hkmanteacherService.selectPage(hkmanteacher, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			hkmanteacherService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Hkmanteacher> all = hkmanteacherService.selectAll(new Hkmanteacher());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("账号", null);
			row.put("名称", null);
			row.put("手机", null);
			row.put("邮箱", null);
			list.add(row);
		} else {
			for (Hkmanteacher hkmanteacher : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("账号", hkmanteacher.getUsername());
				row.put("名称", hkmanteacher.getName());
				row.put("手机", hkmanteacher.getPhone());
				row.put("邮箱", hkmanteacher.getEmail());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=hkmanteacherInfoExcel.xlsx");
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
		List<Hkmanteacher> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Hkmanteacher.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Hkmanteacher info : infoList) {
				try {
					hkmanteacherService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
