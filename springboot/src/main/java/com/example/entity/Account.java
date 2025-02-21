package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Account {
    private Integer id;
    /** 账号 */
    private String username;
    /** 密码 */
    private String password;
    /** 角色 */
    private String role;
    private String newPassword;
    @TableField(exist = false)
    private String token;
}
