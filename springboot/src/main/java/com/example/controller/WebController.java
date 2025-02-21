package com.example.controller;

import com.example.common.config.TokenUtils;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
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

/**
 *  描述：系统用户操作相关接口
 */
@RestController
public class WebController {

	@Resource
	private AdminService adminService;
	@Resource
	private HkmanteacherService hkmanteacherService;
	@Resource
	private HkteacheService hkteacheService;


    /**
     * 描述：用户登录接口
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account loginAccount = null;
		if ("admin".equals(account.getRole())) {
			loginAccount = adminService.login(account);
		}
		if ("hkmanteacher".equals(account.getRole())) {
			loginAccount = hkmanteacherService.login(account);
		}
		if ("hkteache".equals(account.getRole())) {
			loginAccount = hkteacheService.login(account);
		}

        return Result.success(loginAccount);
    }

    /**
     * 描述：用户注册接口
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
		if ("admin".equals(account.getRole())) {
			adminService.register(account);
		}
		if ("hkmanteacher".equals(account.getRole())) {
			hkmanteacherService.register(account);
		}
		if ("hkteache".equals(account.getRole())) {
			hkteacheService.register(account);
		}

        return Result.success();
    }

    /**
    * 描述：更新密码接口
    */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
		if ("admin".equals(account.getRole())) {
			adminService.updatePassword(account);
		}
		if ("hkmanteacher".equals(account.getRole())) {
			hkmanteacherService.updatePassword(account);
		}
		if ("hkteache".equals(account.getRole())) {
			hkteacheService.updatePassword(account);
		}

        return Result.success();
    }
}
