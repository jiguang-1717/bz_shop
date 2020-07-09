package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.SSOService;
import com.bjsxt.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SSOServiceImpl implements SSOService {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Value("${user_redis_key}")
    private String user_redis_key;

    @Override
    public void insertUser(TbUser tbUser, String userToken) {
        //密码置空
        tbUser.setPassword("");
        redisTemplate.opsForValue().set(user_redis_key+":"+userToken,tbUser,7, TimeUnit.DAYS);
    }

    /**
     * 用户退出登录后清除redis
     * @param userToken
     */
    @Override
    public void logOut(String userToken) {
        redisTemplate.delete(user_redis_key+":"+userToken);
    }
}


