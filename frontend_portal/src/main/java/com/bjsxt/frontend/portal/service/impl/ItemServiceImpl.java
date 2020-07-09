package com.bjsxt.frontend.portal.service.impl;

import com.bjsxt.frontend.portal.feign.CommonItemFeignClient;
import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.portal.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    /**
     * 根据id查商品详情
     * @param itemId
     * @return
     */
    @Override
    public Result selectItemInfo(Long itemId) {
        //查缓存
        try{
            TbItem tbItem = commonRedisFeignClient.selectItem(itemId);
            if(tbItem !=null){
                return Result.ok(tbItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查数据库
        TbItem tbItem = commonItemFeignClient.selectItemInfo(itemId);
        if(tbItem !=null){
            try{
                //存入缓存
                commonRedisFeignClient.insertItemByItemId(tbItem);
            }catch (Exception e){
                e.printStackTrace();
            }
            return Result.ok(tbItem);
        }
        return Result.error("查无结果");
    }

    /**
     * 商品详情
     * @param itemId
     * @return
     */
    @Override
    public Result selectItemDescByItemId(Long itemId) {
        TbItemDesc tbItemDesc = commonItemFeignClient.selectItemDescByItemId(itemId);
        if(tbItemDesc !=null){
            return Result.ok(tbItemDesc);
        }
        return Result.error("查无结果");
    }

    /**
     * 商品参数
     * @param itemId
     * @return
     */
    @Override
    public Result selectTbItemParamItemByItemId(Long itemId) {
        TbItemParamItem tbItemParamItem = commonItemFeignClient.selectTbItemParamItemByItemId(itemId);
        if(tbItemParamItem !=null){
            return Result.ok(tbItemParamItem);
        }
        return Result.error("查无结果");
    }
}
