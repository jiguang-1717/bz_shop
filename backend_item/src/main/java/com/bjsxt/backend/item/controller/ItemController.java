package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 商品列表分页显示
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows){
        try{
            return itemService.selectTbItemAllByPage(page,rows);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 商品添加
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem,String desc,String itemParams){

        try{
            return itemService.insertTbItem(tbItem,desc,itemParams);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 商品删除
     * @param itemId
     * @return
     */
    @RequestMapping("/deleteItemById")
    public Result deleteItemById(@RequestParam Long itemId){
        try{
            return itemService.deleteItemById(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 商品预更新信息查询
     * @param itemId
     * @return
     */
    @RequestMapping("/preUpdateItem")
    public Result preUpdateItem(@RequestParam Long itemId){
        try{
            return itemService.preUpdateItem(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }
    /**
     * 商品更新
     */
    @RequestMapping("/updateTbItem")
    public Result updateTbItem(TbItem tbItem,String desc,String itemParams){
        try{
            System.out.println("11111111111");
            return itemService.updateTbItem(tbItem,desc,itemParams);

        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
}
