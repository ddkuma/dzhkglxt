package com.example.common.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.Account;
import com.example.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {

	private static AdminService staticAdminService;
	private static HkmanteacherService staticHkmanteacherService;
	private static HkteacheService staticHkteacheService;

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

	@Resource
	private AdminService adminService;
	@Resource
	private HkmanteacherService hkmanteacherService;
	@Resource
	private HkteacheService hkteacheService;

    @PostConstruct
    public void setUserService() {
		staticAdminService = adminService;
		staticHkmanteacherService = hkmanteacherService;
		staticHkteacheService = hkteacheService;

    }

    /**
     * 生成token
     */
    public static String genToken(String userRole, String password) {
        return JWT.create().withAudience(userRole) // 将 userId-role 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(password)); // 以 password 作为 token 的密钥
    }

    /**
     * 获取当前登录的用户信息
     */
    public static Account getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败， token: {}", token);
                return null;
            }
            // 解析token，获取用户的id
            String userRole = JWT.decode(token).getAudience().get(0);
            String userId = userRole.split("-")[0];
            String role = userRole.split("-")[1];
            Account account = null;
			if ("admin".equals(role)) {
				account = staticAdminService.selectById(Integer.valueOf(userId));
			}
			if ("hkmanteacher".equals(role)) {
				account = staticHkmanteacherService.selectById(Integer.valueOf(userId));
			}
			if ("hkteache".equals(role)) {
				account = staticHkteacheService.selectById(Integer.valueOf(userId));
			}

            return account;
        } catch (Exception e) {
            log.error("获取当前登录的管理员信息失败, token={}", token,  e);
            return null;
        }
    }
}