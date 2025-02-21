package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 短信统计
 */
@Data
@TableName("hk_dxtj")
public class HkDxtj  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 学号 */
	@TableField(exist = false)
	private String studentis;
	/** 姓名 */
	@TableField(exist = false)
	private String studentname;
	/** 手机号 */
	@TableField(exist = false)
	private String studentName;
	/** 发送内容 */
	private String sendContent;
	/** 发送结果 */
	@Alias("发送结果")
	private String sendResults;
	/** 学生管理Id */
	private Integer hkStudentId;



}
