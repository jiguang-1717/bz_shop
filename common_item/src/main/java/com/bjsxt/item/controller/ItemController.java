package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@RestController
@RequestMapping("/service/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 数据列表查询，分页显示
     * @return
     */
    @RequestMapping("/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(@RequestParam Integer page, @RequestParam Integer rows){
        return itemService.selectTbItemAllByPage(page,rows);
    }

    /**
     * 添加商品
     * @param tbItem
     * @return
     */
    @RequestMapping("/insertItem")
    public Integer insertItem(@RequestBody TbItem tbItem){
        return itemService.insertItem(tbItem);
    }

    @RequestMapping("/deleteItemById")
    public Integer deleteItemById(@RequestBody TbItem tbItem){
        return itemService.updateItem(tbItem);
    }

    /**
     * 商品的预更新查询
     * @param itemId
     * @return
     */
    @RequestMapping("/preUpdateItem")
    public Map<String,Object> preUpdateItem(@RequestParam Long itemId){

        return itemService.preUpdateItem(itemId);
    }

    /**
     * 商品的更新
     * @param tbItem
     * @return
     */
    @RequestMapping("/updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem){
        return itemService.updateItem(tbItem);
    }

    /**
     * 根据商品id查询商品详情
     */
    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(@RequestParam Long itemId){
        return itemService.selectItemInfo(itemId);
    }

}
