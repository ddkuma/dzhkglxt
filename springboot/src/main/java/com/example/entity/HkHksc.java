package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 贺卡生成
 */
@Data
@TableName("hk_hksc")
public class HkHksc  {

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
	private String studentClass;
	/** 是否生成 */
	@Alias("是否生成")
	private String generate;
	/** 贺卡链接 */
	@Alias("贺卡链接")
	private String hkUrl;
	/** 辅导员 */
	@TableField(exist = false)
	private String studentTeacher;
	/** 学生管理Id */
	private Integer hkStudentId;



}
