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
public class HkteacheService {

    @Resource
    private HkteacheMapper hkteacheMapper;

    /**
     * 新增
     */
    public void add(Hkteache hkteache) {
        // 唯一校验
        Hkteache dbHkteache = hkteacheMapper.selectByUsername(hkteache.getUsername());
        if (ObjectUtil.isNotNull(dbHkteache)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(hkteache.getPassword())) {
            hkteache.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(hkteache.getName())) {
            hkteache.setName(hkteache.getUsername());
        }
        hkteache.setRole("hkteache");
        hkteacheMapper.insert(hkteache);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        hkteacheMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            hkteacheMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Hkteache hkteache) {
        hkteacheMapper.updateById(hkteache);
    }

    /**
     * 根据ID查询
     */
    public Hkteache selectById(Integer id) {
        return hkteacheMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Hkteache> selectAll(Hkteache hkteache) {
        return hkteacheMapper.selectAll(hkteache);
    }

    /**
     * 分页查询
     */
    public PageInfo<Hkteache> selectPage(Hkteache hkteache, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Hkteache> list = hkteacheMapper.selectAll(hkteache);

        return PageInfo.of(list);
    }

    /**
     * 注册
     */
    public void register(Account account) {
        Hkteache hkteache = new Hkteache();
        hkteache.setUsername(account.getUsername());
        hkteache.setPassword(account.getPassword());
        this.add(hkteache);
    }

    public Hkteache login(Account account) {
        Hkteache dbHkteache = hkteacheMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbHkteache)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbHkteache.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbHkteache.getId() + "-hkteache";
        String token = TokenUtils.genToken(tokenData, dbHkteache.getPassword());
        dbHkteache.setToken(token);


        return dbHkteache;
    }

    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        Hkteache dbHkteache = hkteacheMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbHkteache)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbHkteache.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbHkteache.setPassword(account.getNewPassword());
        hkteacheMapper.updateById(dbHkteache);
    }

}