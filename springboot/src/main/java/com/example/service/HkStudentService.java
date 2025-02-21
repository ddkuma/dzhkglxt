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
public class HkStudentService {

    @Resource
    private HkStudentMapper hkStudentMapper;

    /**
     * 新增
     */
    public void add(HkStudent hkStudent) {
        hkStudentMapper.insert(hkStudent);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        hkStudentMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            hkStudentMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(HkStudent hkStudent) {
        hkStudentMapper.updateById(hkStudent);
    }

    /**
     * 根据ID查询
     */
    public HkStudent selectById(Integer id) {
        return hkStudentMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<HkStudent> selectAll(HkStudent hkStudent) {
        return hkStudentMapper.selectAll(hkStudent);
    }

    /**
     * 分页查询
     */
    public PageInfo<HkStudent> selectPage(HkStudent hkStudent, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<HkStudent> list = this.selectAll(hkStudent);

        return PageInfo.of(list);
    }

}