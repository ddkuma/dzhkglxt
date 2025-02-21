package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 贺卡发送管理
 */
@Data
@TableName("zh_todaybirthday")
public class ZhTodaybirthday  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 学号 */
	@TableField(exist = false)
	private String studetid;
	/** 姓名 */
	@TableField(exist = false)
	private String studentName;
	/** 班级 */
	@TableField(exist = false)
	private String studentClass;
	/** 辅导员 */
	@TableField(exist = false)
	private String studentsTeacher;
	/** 是否发送成功 */
	@Alias("是否发送成功")
	private String sentSuccessfully;
	/** 发送手机号 */
	@TableField(exist = false)
	private String telephoneNumber;
	/** 贺卡链接 */
	@Alias("贺卡链接")
	private String hkUrl;
	/** 学生管理Id */
	private Integer hkStudentId;



}
