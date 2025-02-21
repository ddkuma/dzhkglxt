package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface HkteacheMapper extends BaseMapper<Hkteache> {

    /**
      * 查询所有
    */
    List<Hkteache> selectAll(Hkteache hkteache);

    /**
      * 根据ID查询
    */
    Hkteache selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);

	@Select("select * from hkteache where `username` = #{name}")
	Hkteache selectByUsername(@Param("name") String userName);



}