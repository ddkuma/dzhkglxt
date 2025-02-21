package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 班级管理
 */
@Data
@TableName("hk_class")
public class HkClass  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 班级ID */
	@Alias("班级ID")
	private Integer classiD;
	/** 班级 */
	@Alias("班级")
	private String className;
	/** 毕业时间 */
	@Alias("毕业时间")
	private String graduationTime;
	/** 是否毕业 */
	@Alias("是否毕业")
	private String graduation;
	/** 辅导员 */
	@Alias("辅导员")
	private String studentTeacher;



}
