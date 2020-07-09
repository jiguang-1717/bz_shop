package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemDescService;
import com.bjsxt.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/itemDesc")
public class ItemDescController {

    @Autowired
    private ItemDescService itemDescService;

    @RequestMapping("/insertItemDesc")
    public Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc){

        return itemDescService.insertItemDesc(tbItemDesc);

    }

    @RequestMapping("/updateTbItemDesc")
    public Integer updateTbItemDesc(@RequestBody TbItemDesc tbItemDesc){
        return itemDescService.updateTbItemDesc(tbItemDesc);
    }

    /**
     * 商品介绍
     */
    @RequestMapping("/selectItemDescByItemId")
    public TbItemDesc selectItemDescByItemId(@RequestParam Long itemId){

        return itemDescService.selectItemDescByItemId(itemId);
    }
}
