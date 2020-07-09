package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/contentCategory")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 查询分类
     * @param parentId
     * @return
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public List<TbContentCategory> selectContentCategoryByParentId(@RequestParam Long parentId){
        return contentCategoryService.selectContentCategoryByParentId(parentId);
    }

    /**
     * 添加分类
     * @param tbContentCategory
     * @return
     */
    @RequestMapping("/insertContentCategory")
    public Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return contentCategoryService.insertContentCategory(tbContentCategory);
    }

    /**
     * 删除内容分类
     */
    @RequestMapping("/deleteContentCategoryById")
    public Integer deleteContentCategoryById(@RequestParam Long categoryId){
        return contentCategoryService.deleteContentCategoryById(categoryId);
    }

    /**
     * 修改内容分类
     */
    @RequestMapping("/updateContentCategory")
    public Integer updateContentCategory(@RequestParam Long id,@RequestParam String name){

        return contentCategoryService.updateContentCategory(id,name);
    }




}
