package com.bjsxt.frontend.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

/**
 * 通过feignClient来调用common_content中的接口
 */
@FeignClient(value = "common-content")
public interface CommonContentFeignClient {

    //---------------/service/content----------------
    @GetMapping("/service/content/selectFrontendContentByAD")
     List<Map<String,Object>> selectFrontendContentByAD();
}
