package com.bjsxt.cart.service.impl;

import com.bjsxt.cart.feign.CommonItemFeignClient;
import com.bjsxt.cart.service.CookieCartService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.CartItem;
import com.bjsxt.utils.CookieUtils;
import com.bjsxt.utils.JsonUtils;
import com.bjsxt.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class CookieCartServiceImpl implements CookieCartService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    /**
     * 将商品添加到购物车
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result addItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        //1.获取临时购物车（cookie）
        Map<String, CartItem> cart=this.getCart(request);
        //2.查询商品
        TbItem item=selectItemById(itemId);
        //3.向购物车中添加商品
        addItemToCart(cart,item,num,itemId);
        //4.将购物车通过Cookie写回客户端浏览器
        addClientCookie(request,response,cart);

        return Result.ok();
    }

    /**
     * 查看购物车
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result showCart(HttpServletRequest request, HttpServletResponse response) {
        Map<String, CartItem> cart = getCart(request);
        List<CartItem> list =new ArrayList<>();
        //取出所有的key
        Set<String> keys=cart.keySet();
        for(String key : keys){
            list.add(cart.get(key));
        }
        return Result.ok(list);

    }

    /**
     * 修改购物车中的商品的数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result updateItemNum(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        Map<String,CartItem> cart=this.getCart(request);
        CartItem cartItem = cart.get(itemId.toString());
        if(cartItem != null){
            cartItem.setNum(num);
        }
        addClientCookie(request,response,cart);
        return Result.ok();
    }

    /**
     * 删除购物车
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result deleteItemFromCart(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        Map<String,CartItem> cart=getCart(request);
        cart.remove(itemId.toString());
        addClientCookie(request,response,cart);
        return Result.ok();
    }


    /**
     * 1.获取购物车
     * @param request
     * @return
     */
    private Map<String, CartItem> getCart(HttpServletRequest request) {
        //判断购物车是否已经存在
        String cartJson= CookieUtils.getCookieValue(request,cart_cookie_name,true);
        if(StringUtils.isBlank(cartJson)){
            //临时购物车不存在
            return new HashMap<String,CartItem>();
        }
        try{
            //如果有购物车，那么需要做json转换
            Map<String,CartItem> map= JsonUtils.jsonToMap(cartJson,CartItem.class);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new HashMap<String,CartItem>();
    }

    /**
     * 2.根据id查询商品
     */
    private TbItem selectItemById(Long itemId) {
        TbItem tbItem = commonItemFeignClient.selectItemInfo(itemId);
        return tbItem;
    }

    /**
     * 3.向购物车中添加商品
     */
    private void addItemToCart(Map<String, CartItem> cart, TbItem item, Integer num, Long itemId) {
        //从购物车中取商品
        CartItem cItem=cart.get(itemId.toString());
        if(cItem==null){
            //没有相同的商品
            CartItem cartItem=new CartItem();
            cartItem.setId(item.getId());
            cartItem.setImage(item.getImage());
            cartItem.setNum(num);
            cartItem.setPrice(item.getPrice());
            cartItem.setSellPoint(item.getSellPoint());
            cartItem.setTitle(item.getTitle());
            cart.put(item.getId().toString(),cartItem);
        }else {
            cItem.setNum(cItem.getNum()+num);
        }

    }

    /**
     * 将购物车通过Cookie写回客户端浏览器
     * @param request
     * @param response
     * @param cart
     */
    private void addClientCookie(HttpServletRequest request, HttpServletResponse response, Map<String, CartItem> cart) {
        String cartJson=JsonUtils.objectToJson(cart);
        CookieUtils.setCookie(request,response,this.cart_cookie_name,cartJson,true);
    }



}
