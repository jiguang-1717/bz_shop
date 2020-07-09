package com.bjsxt.cart.service.impl;

import com.bjsxt.cart.feign.CommonItemFeignClient;
import com.bjsxt.cart.feign.CommonRedisFeignClient;
import com.bjsxt.cart.service.RedisCartService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.CartItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RedisCartServiceImpl implements RedisCartService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;


    @Override
    public Result addItem(Long itemId, Integer num, String userId) {
        //1.查询商品
        TbItem tbItem =selectItemById(itemId);
        //2.获取购物车
        Map<String, CartItem> cart=getCart(userId);
        //3.将商品添加到购物车中
        addItemToCart(cart,tbItem,num,itemId);
        //4.将购物车缓存到redis中
        addCartToRedis(userId,cart);
        return Result.ok();
    }

    @Override
    public Result showCart(String userId) {
        List<CartItem> list=new ArrayList<>();
        Map<String,CartItem> cart=getCart(userId);
        Set<String> keys=cart.keySet();
        for(String key:keys){
            list.add(cart.get(key));
        }
        return Result.ok(list);
    }

    /**
     * 修改购物车中的商品数量
     * @param itemId
     * @param num
     * @param userId
     * @return
     */
    @Override
    public Result updateItemNum(Long itemId, Integer num, String userId) {
        Map<String,CartItem> cart = this.getCart(userId);
        CartItem item = cart.get(itemId.toString());
        if(item != null){
            item.setNum(num);
        }
        //将新的购物车缓存到Redis中
        this.addCartToRedis(userId,cart);
        return Result.ok();
    }

    /**
     * 删除购物车中的商品
     * @param itemId
     * @param userId
     * @return
     */
    @Override
    public Result deleteItemFromCart(Long itemId, String userId) {
        Map<String,CartItem> cart = this.getCart(userId);
        cart.remove(itemId.toString());
        //将新的购物车缓存到Redis中
        this.addCartToRedis(userId,cart);
        return Result.ok();
    }



    /**
     *  //1.查询商品
     * @param itemId
     * @return
     */
    private TbItem selectItemById(Long itemId) {
        return commonItemFeignClient.selectItemInfo(itemId);
    }


    /**
     * 2.获取购物车
     * @return
     */
    private Map<String, CartItem> getCart(String userId) {
        try{
            Map<String,CartItem> cart=this.commonRedisFeignClient.selectCartByUserId(userId);
            if(cart ==null){
                cart=new HashMap<String,CartItem>();
            }
            return cart;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new HashMap<String,CartItem>();
    }

    /**
     * 3.将商品添加到购物车中
     * @param cart
     * @param
     * @param num
     * @param itemId
     */
    private void addItemToCart(Map<String, CartItem> cart, TbItem item, Integer num, Long itemId) {
        CartItem cItem = cart.get(itemId.toString());
        if(cItem == null){
            //没有相同的商品
            CartItem cartItem = new CartItem();
            cartItem.setId(item.getId());
            cartItem.setImage(item.getImage());
            cartItem.setNum(num);
            cartItem.setPrice(item.getPrice());
            cartItem.setSellPoint(item.getSellPoint());
            cartItem.setTitle(item.getTitle());
            cart.put(item.getId().toString(),cartItem);
        }else{
            cItem.setNum(cItem.getNum()+num);
        }
    }

    /**
     *
     * @param userId
     * @param cart
     */
    private void addCartToRedis(String userId, Map<String, CartItem> cart) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("cart",cart);
        this.commonRedisFeignClient.insertCart(map);
    }


}






