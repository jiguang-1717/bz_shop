package com.bjsxt.backend.item.feign;


import com.bjsxt.pojo.*;
import com.bjsxt.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 通过feignClient来调用common_item中的接口
 */
@FeignClient(value = "common-item")
public interface CommonItemFeignClient {

    //---------------/service/item-----------------
    /**
     * 查询商品列表，分页显示
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/service/item/selectTbItemAllByPage")
    PageResult selectTbItemAllByPage(@RequestParam("page")Integer page, @RequestParam("rows") Integer rows);

    @PostMapping("/service/item/insertItem")
     Integer insertItem(@RequestBody TbItem tbItem);

    @PostMapping("/service/item/deleteItemById")
     Integer deleteItemById(@RequestBody TbItem tbItem);

    @PostMapping("/service/item/preUpdateItem")
    Map<String,Object> preUpdateItem(@RequestParam("itemId") Long itemId);

    @PostMapping("/service/item/updateTbItem")
     Integer updateTbItem(@RequestBody TbItem tbItem);



    //---------------/service/itemCategory-----------
    /**
     * 查询商品类目
     * @param id
     * @return
     */
    @GetMapping("/service/itemCategory/selectItemCategoryByParentId")
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam("id") Long id);

    //----------------/service/itemParam--------------
    /**
     * 查询商品规格参数模板
     * @param itemCatId
     * @return
     */
    @GetMapping("/service/itemParam//selectItemParamByItemCatId")
    TbItemParam selectItemParamByItemCatId(@RequestParam("itemCatId") Long itemCatId);

    @GetMapping("/service/itemParam/selectItemParamAll")
     PageResult selectItemParamAll(@RequestParam("page")Integer page, @RequestParam("rows") Integer rows);

    @PostMapping("/service/itemParam/insertItemParam")
     Integer insertItemParam(@RequestBody TbItemParam tbItemParam);

    @PostMapping("/service/itemParam/deleteItemParamById")
     Integer deleteItemParamById(@RequestParam Long id);

    //--------------/service/itemDesc----------------
    @PostMapping("/service/itemDesc/insertItemDesc")
     Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    @PostMapping("/service/itemDesc/updateTbItemDesc")
     Integer updateTbItemDesc(@RequestBody TbItemDesc tbItemDesc);

    //------------/service/itemParamItem------------
    @PostMapping("/service/itemParamItem/insertItemParamItem")
     Integer insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

    @PostMapping("/service/itemParamItem/updateItemParamItem")
     Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

}
