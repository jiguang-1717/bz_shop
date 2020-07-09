package com.bjsxt.backend.item.controller;


import com.bjsxt.backend.item.service.ItemParamService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/itemParam")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    /**
     * 查询商品规格参数模板
     * @return
     */
    @RequestMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId){
        try{
            return itemParamService.selectItemParamByItemCatId(itemCatId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 规格参数
     */
    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows){
        try{
            return itemParamService.selectItemParamAll(page,rows);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 添加规格参数模板
     */
    @RequestMapping("/insertItemParam")
    public Result insertItemParam(@RequestParam Long itemCatId,@RequestParam String paramData){
        try{
            return itemParamService.insertItemParam(itemCatId,paramData);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }

    /**
     * 删除参数规格模板
     */
    @RequestMapping("/deleteItemParamById")
    public Result deleteItemParamById(@RequestParam Long id){
        try{
            return itemParamService.deleteItemParamById(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.build(500,"error");
    }
}
