package com.xsm.redis.entity;

import java.io.Serializable;

/**
 * @author xsm
 * @Date 2021/1/23 16:07
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -2938298397L;

    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
