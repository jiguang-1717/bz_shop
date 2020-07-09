package com.bjsxt.sso.controller;

import com.bjsxt.pojo.TbUser;
import com.bjsxt.sso.service.SSOService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sso")
public class SSOController {


    @Autowired
    private SSOService ssoService;

    /**
     * 注册验证接口
     * @param checkValue
     * @param checkFlag
     * @return
     */
    @RequestMapping("/checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable String checkValue,@PathVariable Integer checkFlag){
        try{
            return ssoService.checkUserInfo(checkValue,checkFlag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }


    /**
     * 注册接口
     */
    @RequestMapping("/userRegister")
    public Result userRegister(TbUser tbUser){
        try{
            return ssoService.userRegister(tbUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }
    /**
     * 用户登录
     */
    @RequestMapping("/userLogin")
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
        try{
            return ssoService.userLogin(username,password,request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 用户登出
     */
    @RequestMapping("/logOut")
    public Result logOut(String token){
        try{
            return ssoService.logOut(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

}
