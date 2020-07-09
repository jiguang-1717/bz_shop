package com.bjsxt.frontend.portal.service.impl;

import com.bjsxt.frontend.portal.feign.CommonItemFeignClient;
import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.portal.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;
    /**
     * 前台左侧商品分类显示
     * @return
     */
    @Override
    public Result selectItemCategoryAll() {
        //查缓冲  这里抛异常是为了防止缓冲操作失败，导致干扰正常的数据库查询
        try{
            CatResult catResult = commonRedisFeignClient.selectItemCategory();
            //判断缓存是否命中
            if(catResult !=null && catResult.getData() !=null && catResult.getData().size()>0){

                return Result.ok(catResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查数据库
        CatResult catResult = commonItemFeignClient.selectItemCategoryAll();
        //将数据库中的查询的数据，存入缓存
        if(catResult !=null && catResult.getData() !=null && catResult.getData().size()>0){
            try{
                commonRedisFeignClient.insertItemCategory(catResult);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(catResult !=null && catResult.getData() !=null && catResult.getData().size()>0){
            return Result.ok(catResult);
        }
        return Result.error("查无结果");

    }
}
