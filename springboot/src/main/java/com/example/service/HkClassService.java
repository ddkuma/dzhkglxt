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
public class HkClassService {

    @Resource
    private HkClassMapper hkClassMapper;

    /**
     * 新增
     */
    public void add(HkClass hkClass) {
        hkClassMapper.insert(hkClass);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        hkClassMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            hkClassMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(HkClass hkClass) {
        hkClassMapper.updateById(hkClass);
    }

    /**
     * 根据ID查询
     */
    public HkClass selectById(Integer id) {
        return hkClassMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<HkClass> selectAll(HkClass hkClass) {
        return hkClassMapper.selectAll(hkClass);
    }

    /**
     * 分页查询
     */
    public PageInfo<HkClass> selectPage(HkClass hkClass, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<HkClass> list = this.selectAll(hkClass);

        return PageInfo.of(list);
    }

}