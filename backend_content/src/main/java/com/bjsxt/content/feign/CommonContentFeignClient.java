package com.bjsxt.content.feign;


import com.bjsxt.pojo.TbContent;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通过feignClient来调用common_content中的接口
 */
@FeignClient(value = "common-content")
public interface CommonContentFeignClient {

    //-------------------/service/contentCategory-----------
    @GetMapping("/service/contentCategory/selectContentCategoryByParentId")
     List<TbContentCategory> selectContentCategoryByParentId(@RequestParam Long parentId);

    @PostMapping("/service/contentCategory/insertContentCategory")
     Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory);

    @PostMapping("/service/contentCategory/deleteContentCategoryById")
     Integer deleteContentCategoryById(@RequestParam Long categoryId);

    @PostMapping("/service/contentCategory/updateContentCategory")
     Integer updateContentCategory(@RequestParam Long id,@RequestParam String name);


    //-------------------/service/content--------------
    @PostMapping("/service/content/selectTbContentAllByCategoryId")
     PageResult selectTbContentAllByCategoryId(@RequestParam Long categoryId);

    @PostMapping("/service/content/insertTbContent")
     Integer insertTbContent(@RequestBody TbContent tbContent);

    @PostMapping("/service/content/deleteContentByIds")
     Integer deleteContentByIds(@RequestParam Long ids);
}
