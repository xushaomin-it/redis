package com.xsm.redis.mapper;

import com.xsm.redis.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author xsm
 * @Date 2021/1/23 15:58
 */
@Mapper
public interface StudentMapper {

    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

}
