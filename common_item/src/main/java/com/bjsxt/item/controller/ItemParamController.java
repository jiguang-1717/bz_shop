package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemParamService;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 查询商品规格参数模板接口
 */
@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(@RequestParam Long itemCatId){

        return itemParamService.selectItemParamByItemCatId(itemCatId);
    }

    /**
     * 查询规格参数
     */
    @RequestMapping("/selectItemParamAll")
    public PageResult selectItemParamAll(@RequestParam Integer page,@RequestParam Integer rows){
        return itemParamService.selectItemParamAll(page,rows);
    }

    /**
     * 添加规格参数
     */
    @RequestMapping("/insertItemParam")
    public Integer insertItemParam(@RequestBody TbItemParam tbItemParam){

        return itemParamService.insertItemParam(tbItemParam);
    }

    /**
     * 删除参数规格模板
     */
    @RequestMapping("/deleteItemParamById")
    public Integer deleteItemParamById(@RequestParam Long id){

        return itemParamService.deleteItemParamById(id);
    }

}
