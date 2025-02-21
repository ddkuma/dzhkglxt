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
*  描述：贺卡管理相关接口
*/
@RestController
@RequestMapping("/hkHk")
public class HkHkController {

    @Resource
    HkHkService hkHkService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody HkHk hkHk) {

        hkHkService.add(hkHk);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        hkHkService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        hkHkService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody HkHk hkHk) {

        hkHkService.updateById(hkHk);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        HkHk hkHk = hkHkService.selectById(id);
        return Result.success(hkHk);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(HkHk hkHk) {
        List<HkHk> list = hkHkService.selectAll(hkHk);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            HkHk hkHk,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<HkHk> pageInfo = hkHkService.selectPage(hkHk, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			hkHkService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<HkHk> all = hkHkService.selectAll(new HkHk());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("学号", null);
			row.put("姓名", null);
			row.put("班级", null);
			row.put("电话号", null);
			row.put("是否生成成功", null);
			row.put("是否发送成功", null);
			row.put("辅导员", null);
			list.add(row);
		} else {
			for (HkHk hkHk : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("学号", hkHk.getStudentid());
				row.put("姓名", hkHk.getStudentName());
				row.put("班级", hkHk.getStudentCalss());
				row.put("电话号", hkHk.getTelephoneNumber());
				row.put("是否生成成功", hkHk.getGenerate());
				row.put("是否发送成功", hkHk.getSentSuccessfully());
				row.put("辅导员", hkHk.getStudentTeache());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=hkHkInfoExcel.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		writer.close();
		IoUtil.close(System.out);
	}


}
