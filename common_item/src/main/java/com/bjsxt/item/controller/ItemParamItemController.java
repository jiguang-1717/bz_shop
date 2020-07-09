package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemParamItemService;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/itemParamItem")
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/insertItemParamItem")
    public Integer insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        return itemParamItemService.insertItemParamItem(tbItemParamItem);
    }

    @RequestMapping("/updateItemParamItem")
    public Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){

        return itemParamItemService.updateItemParamItem(tbItemParamItem);
    }

    @RequestMapping("/selectTbItemParamItemByItemId")
    public TbItemParamItem selectTbItemParamItemByItemId(@RequestParam Long itemId){

        return itemParamItemService.selectTbItemParamItemByItemId(itemId);
    }
}
