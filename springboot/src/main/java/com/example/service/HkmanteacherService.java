package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.mapper.*;
import com.example.common.config.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HkmanteacherService {

    @Resource
    private HkmanteacherMapper hkmanteacherMapper;

    /**
     * 新增
     */
    public void add(Hkmanteacher hkmanteacher) {
        // 唯一校验
        Hkmanteacher dbHkmanteacher = hkmanteacherMapper.selectByUsername(hkmanteacher.getUsername());
        if (ObjectUtil.isNotNull(dbHkmanteacher)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(hkmanteacher.getPassword())) {
            hkmanteacher.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(hkmanteacher.getName())) {
            hkmanteacher.setName(hkmanteacher.getUsername());
        }
        hkmanteacher.setRole("hkmanteacher");
        hkmanteacherMapper.insert(hkmanteacher);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        hkmanteacherMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            hkmanteacherMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Hkmanteacher hkmanteacher) {
        hkmanteacherMapper.updateById(hkmanteacher);
    }

    /**
     * 根据ID查询
     */
    public Hkmanteacher selectById(Integer id) {
        return hkmanteacherMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Hkmanteacher> selectAll(Hkmanteacher hkmanteacher) {
        return hkmanteacherMapper.selectAll(hkmanteacher);
    }

    /**
     * 分页查询
     */
    public PageInfo<Hkmanteacher> selectPage(Hkmanteacher hkmanteacher, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Hkmanteacher> list = hkmanteacherMapper.selectAll(hkmanteacher);

        return PageInfo.of(list);
    }

    /**
     * 注册
     */
    public void register(Account account) {
        Hkmanteacher hkmanteacher = new Hkmanteacher();
        hkmanteacher.setUsername(account.getUsername());
        hkmanteacher.setPassword(account.getPassword());
        this.add(hkmanteacher);
    }

    public Hkmanteacher login(Account account) {
        Hkmanteacher dbHkmanteacher = hkmanteacherMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbHkmanteacher)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbHkmanteacher.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbHkmanteacher.getId() + "-hkmanteacher";
        String token = TokenUtils.genToken(tokenData, dbHkmanteacher.getPassword());
        dbHkmanteacher.setToken(token);


        return dbHkmanteacher;
    }

    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        Hkmanteacher dbHkmanteacher = hkmanteacherMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbHkmanteacher)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbHkmanteacher.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbHkmanteacher.setPassword(account.getNewPassword());
        hkmanteacherMapper.updateById(dbHkmanteacher);
    }

}