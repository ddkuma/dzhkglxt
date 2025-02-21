package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 学生管理
 */
@Data
@TableName("hk_student")
public class HkStudent  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 学号 */
	@Alias("学号")
	private String studentid;
	/** 学生姓名 */
	@Alias("学生姓名")
	private String studentname;
	/** 生日 */
	@Alias("生日")
	private String birthday;
	/** 免打扰 */
	@Alias("免打扰")
	private String notDisturb;
	/** 辅导员 */
	@Alias("辅导员")
	private String studentTeacher;
	/** 班级 */
	@TableField(exist = false)
	private String studentClass;
	/** 电话号码 */
	@Alias("电话号码")
	private Integer telephoneNumber;
	/** 班级管理Id */
	private Integer hkClassId;



}
