package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.ItemService;
import com.bjsxt.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    /**
     * 向缓存中存数据
     */
    @RequestMapping("/insertItem")
    public void insertItemByItemId(@RequestBody TbItem tbItem){
        itemService.insertItemByItemId(tbItem);
    }

    /**
     * 缓存命中
     */
    @RequestMapping("/selectItem")
    public TbItem selectItem(@RequestParam Long ItemId){
        return itemService.selectItem(ItemId);
    }
}
