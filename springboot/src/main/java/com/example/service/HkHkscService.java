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
public class HkHkscService {

    @Resource
    private HkHkscMapper hkHkscMapper;

    /**
     * 新增
     */
    public void add(HkHksc hkHksc) {
        hkHkscMapper.insert(hkHksc);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        hkHkscMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            hkHkscMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(HkHksc hkHksc) {
        hkHkscMapper.updateById(hkHksc);
    }

    /**
     * 根据ID查询
     */
    public HkHksc selectById(Integer id) {
        return hkHkscMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<HkHksc> selectAll(HkHksc hkHksc) {
        return hkHkscMapper.selectAll(hkHksc);
    }

    /**
     * 分页查询
     */
    public PageInfo<HkHksc> selectPage(HkHksc hkHksc, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<HkHksc> list = this.selectAll(hkHksc);

        return PageInfo.of(list);
    }

}