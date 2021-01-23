package com.xsm.redis.topic;

import com.alibaba.fastjson.JSON;
import com.xsm.redis.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author xsm
 * @Date 2021/1/23 16:49
 */
@Component
public class MessageReceiveTwo {
    public void getMessage(String object){
        //序列化对象（特别注意：发布的时候需要设置序列化；订阅方也需要设置序列化）
        User user = JSON.parseObject(object, User.class);
        System.out.println("消息客户端2号:" + user);
    }
}
