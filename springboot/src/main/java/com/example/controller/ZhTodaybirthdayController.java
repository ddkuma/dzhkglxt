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
*  描述：贺卡发送管理相关接口
*/
@RestController
@RequestMapping("/zhTodaybirthday")
public class ZhTodaybirthdayController {

    @Resource
    ZhTodaybirthdayService zhTodaybirthdayService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody ZhTodaybirthday zhTodaybirthday) {

        zhTodaybirthdayService.add(zhTodaybirthday);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        zhTodaybirthdayService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        zhTodaybirthdayService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody ZhTodaybirthday zhTodaybirthday) {

        zhTodaybirthdayService.updateById(zhTodaybirthday);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ZhTodaybirthday zhTodaybirthday = zhTodaybirthdayService.selectById(id);
        return Result.success(zhTodaybirthday);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(ZhTodaybirthday zhTodaybirthday) {
        List<ZhTodaybirthday> list = zhTodaybirthdayService.selectAll(zhTodaybirthday);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            ZhTodaybirthday zhTodaybirthday,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<ZhTodaybirthday> pageInfo = zhTodaybirthdayService.selectPage(zhTodaybirthday, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			zhTodaybirthdayService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<ZhTodaybirthday> all = zhTodaybirthdayService.selectAll(new ZhTodaybirthday());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("学号", null);
			row.put("姓名", null);
			row.put("班级", null);
			row.put("辅导员", null);
			row.put("是否发送成功", null);
			row.put("发送手机号", null);
			row.put("贺卡链接", null);
			list.add(row);
		} else {
			for (ZhTodaybirthday zhTodaybirthday : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("学号", zhTodaybirthday.getStudetid());
				row.put("姓名", zhTodaybirthday.getStudentName());
				row.put("班级", zhTodaybirthday.getStudentClass());
				row.put("辅导员", zhTodaybirthday.getStudentsTeacher());
				row.put("是否发送成功", zhTodaybirthday.getSentSuccessfully());
				row.put("发送手机号", zhTodaybirthday.getTelephoneNumber());
				row.put("贺卡链接", zhTodaybirthday.getHkUrl());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=zhTodaybirthdayInfoExcel.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		writer.close();
		IoUtil.close(System.out);
	}


}
