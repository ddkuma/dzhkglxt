package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.config.TokenUtils;
import com.example.mapper.*;
import com.example.entity.*;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ZhTodaybirthdayService {

    @Resource
    private ZhTodaybirthdayMapper zhTodaybirthdayMapper;

    /**
     * 新增
     */
    public void add(ZhTodaybirthday zhTodaybirthday) {
        zhTodaybirthdayMapper.insert(zhTodaybirthday);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        zhTodaybirthdayMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            zhTodaybirthdayMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(ZhTodaybirthday zhTodaybirthday) {
        zhTodaybirthdayMapper.updateById(zhTodaybirthday);
    }

    /**
     * 根据ID查询
     */
    public ZhTodaybirthday selectById(Integer id) {
        return zhTodaybirthdayMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<ZhTodaybirthday> selectAll(ZhTodaybirthday zhTodaybirthday) {
        return zhTodaybirthdayMapper.selectAll(zhTodaybirthday);
    }

    /**
     * 分页查询
     */
    public PageInfo<ZhTodaybirthday> selectPage(ZhTodaybirthday zhTodaybirthday, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<ZhTodaybirthday> list = this.selectAll(zhTodaybirthday);

        return PageInfo.of(list);
    }

}