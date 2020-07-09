package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    /**
     * 内容管理 查询接口
     * @param categoryId
     * @return
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(@RequestParam Long categoryId){
        return contentService.selectTbContentAllByCategoryId(categoryId);
    }

    /**
     * 内容管理 添加接口
     * @param tbContent
     * @return
     */
    @RequestMapping("/insertTbContent")
    public Integer insertTbContent(@RequestBody TbContent tbContent){

        return contentService.insertTbContent(tbContent);
    }

    /**
     * 内容管理 删除接口
     */
    @RequestMapping("/deleteContentByIds")
    public Integer deleteContentByIds(@RequestParam Long ids){
        return contentService.deleteContentByIds(ids);
    }

    /**
     * 前台大广告位置
     */
    @RequestMapping("/selectFrontendContentByAD")
    public List<Map<String,Object>> selectFrontendContentByAD(){

        return contentService.selectFrontendContentByAD();
    }

}
