package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    /**
     * 将大广告为的数据添加到缓存中
     */
    @RequestMapping("/insertContentAD")
    public void insertContentAD(@RequestBody List<Map<String,Object>> list){
        contentService.insertContentAD(list);
    }

    /**
     * 在缓存中查询大广告位置的数据
     */
    @RequestMapping("/selectContentAD")
    public List<Map<String,Object>> selectContentAD(){
        return contentService.selectContentAD();
    }
}
