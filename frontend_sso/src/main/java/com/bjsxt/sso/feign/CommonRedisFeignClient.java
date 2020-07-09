package com.bjsxt.sso.feign;

import com.bjsxt.pojo.TbUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-redis")
public interface CommonRedisFeignClient {

    //--------------------------/sso/redis
    @PostMapping("/sso/redis/insertUser")
     void insertUser(@RequestBody TbUser tbUser, @RequestParam String userToken);

    @PostMapping("/sso/redis/logOut")
     void logOut(@RequestParam String userToken);



}
