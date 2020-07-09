package com.bjsxt.frontend.portal.controller;

import com.bjsxt.frontend.portal.service.ItemService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/selectItemInfo")
    public Result selectItemInfo(Long itemId){
        try{
            return itemService.selectItemInfo(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 商品简介
     * @param itemId
     * @return
     */
    @RequestMapping("/selectItemDescByItemId")
    public Result selectItemDescByItemId(Long itemId){
        try{
            return itemService.selectItemDescByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 商品规格
     */
    @RequestMapping("/selectTbItemParamItemByItemId")
    public Result selectTbItemParamItemByItemId(Long itemId){
        try{
            return itemService.selectTbItemParamItemByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }
}
