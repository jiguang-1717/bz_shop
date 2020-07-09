package com.bjsxt.frontend.portal.feign;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 通过feignClient来调用common_item中的接口
 */
@FeignClient(value = "common-item")
public interface CommonItemFeignClient {

    //--------------------/service/itemCategory-------
    @GetMapping("/service/itemCategory/selectItemCategoryAll")
     CatResult selectItemCategoryAll();

    //------------------/service/item---------------
    @PostMapping("/service/item/selectItemInfo")
     TbItem selectItemInfo(@RequestParam Long itemId);

    @PostMapping("/service/itemDesc/selectItemDescByItemId")
     TbItemDesc selectItemDescByItemId(@RequestParam Long itemId);

    //------------------/service/itemParamItem------------
    @PostMapping("/service/itemParamItem/selectTbItemParamItemByItemId")
     TbItemParamItem selectTbItemParamItemByItemId(@RequestParam Long itemId);


}
