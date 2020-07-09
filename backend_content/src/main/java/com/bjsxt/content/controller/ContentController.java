package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 内容管理 查询接口
     * @param categoryId
     * @return
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam Long categoryId){
        try{
            return contentService.selectTbContentAllByCategoryId(categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 内容管理 添加接口
     */
    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent){
        try{
            return contentService.insertTbContent(tbContent);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 内容管理 删除接口
     */
    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids){
        try{
            return contentService.deleteContentByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }


}
