package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {

    /**
      * 查询所有
    */
    List<Admin> selectAll(Admin admin);

    /**
      * 根据ID查询
    */
    Admin selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);

	@Select("select * from admin where `username` = #{name}")
	Admin selectByUsername(@Param("name") String userName);



}