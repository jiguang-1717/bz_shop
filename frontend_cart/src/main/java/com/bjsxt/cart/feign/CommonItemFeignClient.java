package com.bjsxt.cart.feign;

import com.bjsxt.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-item")
public interface CommonItemFeignClient {


    //---------------------/service/item----------
    @PostMapping("/service/item/selectItemInfo")
     TbItem selectItemInfo(@RequestParam Long itemId);
}
