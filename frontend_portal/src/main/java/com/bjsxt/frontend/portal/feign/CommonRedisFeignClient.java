package com.bjsxt.frontend.portal.feign;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 * 通过feignClient来调用common_redis中的接口
 */
@FeignClient(value = "common-redis")
public interface CommonRedisFeignClient {

    //---------------------/redis/itemCategory
    @PostMapping("/redis/itemCategory/insertItemCategory")
    void insertItemCategory(@RequestBody CatResult catResult);

    @PostMapping("/redis/itemCategory/selectItemCategory")
    CatResult selectItemCategory();

    //-----------------------/redis/content-------------
    @PostMapping("/redis/content/insertContentAD")
     void insertContentAD(@RequestBody List<Map<String,Object>> list);

    @PostMapping("/redis/content/selectContentAD")
     List<Map<String,Object>> selectContentAD();

    //------------------------redis/item-----------------------
    @PostMapping("/redis/item/insertItem")
     void insertItemByItemId(@RequestBody TbItem tbItem);

    @PostMapping("/redis/item/selectItem")
     TbItem selectItem(@RequestParam Long itemId);
}
