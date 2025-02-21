package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 贺卡管理
 */
@Data
@TableName("hk_hk")
public class HkHk  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 学号 */
	@TableField(exist = false)
	private String studentid;
	/** 姓名 */
	@TableField(exist = false)
	private String studentName;
	/** 班级 */
	@TableField(exist = false)
	private String studentCalss;
	/** 电话号 */
	@TableField(exist = false)
	private String telephoneNumber;
	/** 是否生成成功 */
	@TableField(exist = false)
	private String generate;
	/** 是否发送成功 */
	@TableField(exist = false)
	private String sentSuccessfully;
	/** 辅导员 */
	@TableField(exist = false)
	private String studentTeache;
	/** 学生管理Id */
	private Integer hkStudentId;
	/** 贺卡生成Id */
	private Integer hkHkscId;
	/** 贺卡发送管理Id */
	private Integer zhTodaybirthdayId;



}
