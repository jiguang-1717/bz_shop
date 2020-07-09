package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemCategoryService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/selectItemCategoryByParentId")
    public Result selectItemCategoryByParentId(@RequestParam(defaultValue = "0") Long id){

        try{
            return itemCategoryService.selectItemCategoryByParentId(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }


}
