package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 分类内容管理 查询
     * @param id
     * @return
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(@RequestParam(defaultValue = "0") Long id){

        try{
            return contentCategoryService.selectContentCategoryByParentId(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 添加内容
     * @return
     */
    @RequestMapping("/insertContentCategory")
    public Result insertContentCategory(@RequestParam Long parentId,@RequestParam String name){
        try{
            return contentCategoryService.insertContentCategory(parentId,name);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 删除内容分类
     */
    @RequestMapping("/deleteContentCategoryById")
    public Result deleteContentCategoryById(@RequestParam Long categoryId){
        try{
            return contentCategoryService.deleteContentCategoryById(categoryId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 修改内容分类
     */
    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(@RequestParam Long id,@RequestParam String name){
        try{
            return contentCategoryService.updateContentCategory(id,name);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }
}
