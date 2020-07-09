package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.ItemService;
import com.bjsxt.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {



    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${frontend_item_redis_key}")
    private String frontend_item_redis_key;

    /**
     * 写入缓存
     * @param tbItem
     */
    @Override
    public void insertItemByItemId(TbItem tbItem) {
        redisTemplate.opsForValue().set(frontend_item_redis_key+tbItem.getId(),tbItem);
    }

    /**
     * 查询缓存
     * @return
     */
    @Override
    public TbItem selectItem(Long ItemId) {
        TbItem  tbItem = (TbItem) redisTemplate.opsForValue().get(frontend_item_redis_key+ItemId);
        return tbItem;
    }
}
