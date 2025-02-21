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
public class HkHkService {

    @Resource
    private HkHkMapper hkHkMapper;

    /**
     * 新增
     */
    public void add(HkHk hkHk) {
        hkHkMapper.insert(hkHk);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        hkHkMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            hkHkMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(HkHk hkHk) {
        hkHkMapper.updateById(hkHk);
    }

    /**
     * 根据ID查询
     */
    public HkHk selectById(Integer id) {
        return hkHkMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<HkHk> selectAll(HkHk hkHk) {
        return hkHkMapper.selectAll(hkHk);
    }

    /**
     * 分页查询
     */
    public PageInfo<HkHk> selectPage(HkHk hkHk, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<HkHk> list = this.selectAll(hkHk);

        return PageInfo.of(list);
    }

}