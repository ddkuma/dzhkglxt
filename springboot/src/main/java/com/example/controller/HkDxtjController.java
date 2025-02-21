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
*  描述：短信统计相关接口
*/
@RestController
@RequestMapping("/hkDxtj")
public class HkDxtjController {

    @Resource
    HkDxtjService hkDxtjService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody HkDxtj hkDxtj) {

        hkDxtjService.add(hkDxtj);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        hkDxtjService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        hkDxtjService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody HkDxtj hkDxtj) {

        hkDxtjService.updateById(hkDxtj);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        HkDxtj hkDxtj = hkDxtjService.selectById(id);
        return Result.success(hkDxtj);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(HkDxtj hkDxtj) {
        List<HkDxtj> list = hkDxtjService.selectAll(hkDxtj);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            HkDxtj hkDxtj,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<HkDxtj> pageInfo = hkDxtjService.selectPage(hkDxtj, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			hkDxtjService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<HkDxtj> all = hkDxtjService.selectAll(new HkDxtj());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("学号", null);
			row.put("姓名", null);
			row.put("手机号", null);
			row.put("发送结果", null);
			list.add(row);
		} else {
			for (HkDxtj hkDxtj : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("学号", hkDxtj.getStudentis());
				row.put("姓名", hkDxtj.getStudentname());
				row.put("手机号", hkDxtj.getStudentName());
				row.put("发送结果", hkDxtj.getSendResults());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=hkDxtjInfoExcel.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		writer.close();
		IoUtil.close(System.out);
	}


}
