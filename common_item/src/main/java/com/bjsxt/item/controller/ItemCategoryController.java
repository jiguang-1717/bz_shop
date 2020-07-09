package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemCategoryService;
import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品类目查询
 */
@RestController
@RequestMapping("/service/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 查询商品类目
     * @return
     */
    @RequestMapping("/selectItemCategoryByParentId")
   public List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long id){
       return itemCategoryService.selectItemCategoryByParentId(id);
   }

    /**
     * 前台左侧商品分类
     */
    @RequestMapping("/selectItemCategoryAll")
    public CatResult selectItemCategoryAll(){
        return itemCategoryService.selectItemCategoryAll();
    }
}
