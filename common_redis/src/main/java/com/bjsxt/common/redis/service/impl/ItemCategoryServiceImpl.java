package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${frontend_catresult_Redis_key}")
    private String frontend_catresult_Redis_key;

    /**
     * 向redis中添加商品类目缓存数据
     * @param catResult
     */
    @Override
    public void insertItemCategory(CatResult catResult) {
        redisTemplate.opsForValue().set(frontend_catresult_Redis_key,catResult);

    }

    /**
     * 从redis中查询商品类目缓存
     * @return
     */
    @Override
    public CatResult selectItemCategory() {
        CatResult catResult = (CatResult) redisTemplate.opsForValue().get(frontend_catresult_Redis_key);
        return catResult;
    }

}
