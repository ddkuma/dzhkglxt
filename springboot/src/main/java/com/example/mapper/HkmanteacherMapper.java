package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface HkmanteacherMapper extends BaseMapper<Hkmanteacher> {

    /**
      * 查询所有
    */
    List<Hkmanteacher> selectAll(Hkmanteacher hkmanteacher);

    /**
      * 根据ID查询
    */
    Hkmanteacher selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);

	@Select("select * from hkmanteacher where `username` = #{name}")
	Hkmanteacher selectByUsername(@Param("name") String userName);



}