package com.bjsxt.cart.controller;

import com.bjsxt.cart.service.CookieCartService;
import com.bjsxt.cart.service.RedisCartService;
import com.bjsxt.utils.Result;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CookieCartService cookieCartService;
    @Autowired
    private RedisCartService redisCartService;
    /**
     * 将商品添加如购物车
     */
    @RequestMapping("/addItem")
    public Result addItem(Long itemId, String userId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        System.out.println("11111111");
        try{
            if(StringUtils.isBlank(userId)){
                //用户未登录的时候
                return cookieCartService.addItem(itemId,num,request,response);
            }else{
                //用户已登录的时候
               return redisCartService.addItem(itemId,num,userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 查看购物车
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/showCart")
    public Result showCart(String userId,HttpServletRequest  request,HttpServletResponse response){
        try{
            if(StringUtils.isBlank(userId)){
                //用户未登录的时候
                return cookieCartService.showCart(request,response);
            }else{
                //用户已登录的时候
                return redisCartService.showCart(userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 修改购物车中的商品的数量
     */
    @RequestMapping("/updateItemNum")
    public Result updateItemNum(Long itemId,String userId,Integer num,HttpServletRequest request,HttpServletResponse response){

        try{
            if(StringUtils.isBlank(userId)){
                //用户未登录的时候
                return cookieCartService.updateItemNum(itemId,num,request,response);
            }else{
                //用户已登录的时候
                return redisCartService.updateItemNum(itemId,num,userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }

    /**
     * 删除购物车中的商品
     */
    @RequestMapping("/deleteItemFromCart")
    public Result deleteItemFromCart(Long itemId,String userId,HttpServletRequest request,HttpServletResponse response){
        try{
            if(StringUtils.isBlank(userId)){
                //用户未登录的时候
                return cookieCartService.deleteItemFromCart(itemId,request,response);
            }else{
                //用户已登录的时候
                return redisCartService.deleteItemFromCart(itemId,userId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error");
    }


}
