package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.SSOService;
import com.bjsxt.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sso/redis")
public class SSOController {

    @Autowired
    private SSOService ssoService;
    /**
     * 将用户缓存到redis中
     */
    @RequestMapping("/insertUser")
    public void insertUser(@RequestBody TbUser tbUser,@RequestParam String userToken){
        ssoService.insertUser(tbUser,userToken);
    }

    /**
     * 用户退出登录
     */
    @RequestMapping("/logOut")
    public void logOut(@RequestParam String userToken){
        ssoService.logOut(userToken);
    }
}
