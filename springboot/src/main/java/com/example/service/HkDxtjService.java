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
public class HkDxtjService {

    @Resource
    private HkDxtjMapper hkDxtjMapper;

    /**
     * 新增
     */
    public void add(HkDxtj hkDxtj) {
        hkDxtjMapper.insert(hkDxtj);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        hkDxtjMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            hkDxtjMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(HkDxtj hkDxtj) {
        hkDxtjMapper.updateById(hkDxtj);
    }

    /**
     * 根据ID查询
     */
    public HkDxtj selectById(Integer id) {
        return hkDxtjMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<HkDxtj> selectAll(HkDxtj hkDxtj) {
        return hkDxtjMapper.selectAll(hkDxtj);
    }

    /**
     * 分页查询
     */
    public PageInfo<HkDxtj> selectPage(HkDxtj hkDxtj, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<HkDxtj> list = this.selectAll(hkDxtj);

        return PageInfo.of(list);
    }

}