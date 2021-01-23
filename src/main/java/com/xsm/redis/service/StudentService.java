package com.xsm.redis.service;

import cn.tanzhou.support.commons.redis.service.RedisService;
import com.xsm.redis.entity.Student;
import com.xsm.redis.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author xsm
 * @Date 2021/1/23 16:05
 */
@Service
public class StudentService{

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 缓存穿透
     * redis 缓存穿透指查询一个一定不存在的数据,由于缓存不命中,并且处于容错考虑, 如果从存储层查不到数据则不写缓存,
     * 这将导致这个不存在的数据每次请求都要到存储层去查询,失去了缓存的意义
     *
     * 危害
     * 对底层数据源压力过大, 有些底层数据源不具备高并发量
     *
     * 解决:
     * 缓存空对象
     */

    public Student getById(Integer id){
        Student student = redisService.getObject("student:" + id,  Student.class);
        if (student != null) {
            if (student instanceof NullValueEntity) {
                return null;
            }
            return student;
        }
        student = studentMapper.getById(id);
        if (student != null) {
            redisService.setObject("student:" + id, student);
        } else {
            redisService.setObject("student:" + id, new NullValueEntity());
        }
        return student;
    }

    public static class NullValueEntity extends Student implements Serializable {
        private static final long serialVersionUID = -2938298397L;
    }


}
