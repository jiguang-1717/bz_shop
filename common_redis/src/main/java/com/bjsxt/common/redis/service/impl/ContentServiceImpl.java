package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${frontend_ad_redis_key}")
    private String frontend_ad_redis_key;
    /**
     * 向缓存中添加大广告位的数据
      * @param list
     */
    @Override
    public void insertContentAD(List<Map<String, Object>> list) {
        redisTemplate.opsForValue().set(frontend_ad_redis_key,list);
    }

    /**
     * 在缓存中查询大广告位的数据
     * @return
     */
    @Override
    public List<Map<String, Object>> selectContentAD() {
        List<Map<String,Object>> list= (List<Map<String, Object>>) redisTemplate.opsForValue().get(frontend_ad_redis_key);
        return list;
    }
}
