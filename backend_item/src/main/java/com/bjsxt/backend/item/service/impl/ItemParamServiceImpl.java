package com.bjsxt.backend.item.service.impl;

import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.backend.item.service.ItemParamService;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    /**
     * 规格参数
     * @return
     */
    @Override
    public Result selectItemParamAll(Integer page,Integer rows) {
        PageResult result = commonItemFeignClient.selectItemParamAll(page, rows);
        if(result !=null && result.getResult().size()>0){
            return Result.ok(result);
        }
        return Result.error("查无结果");
    }

    /**
     * 添加规格模板
     * @param itemCatId
     * @param paramData
     * @return
     */
    @Override
    public Result insertItemParam(Long itemCatId, String paramData) {
        //补齐TbItemParam
        TbItemParam tbItemParam=new TbItemParam();
        tbItemParam.setItemCatId(itemCatId);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        Integer itemParamNum = commonItemFeignClient.insertItemParam(tbItemParam);
        if(1==itemParamNum){
            return Result.ok();
        }

        return Result.error("添加失败");
    }

    /**
     * 删除参数规格模板
     * @param id
     * @return
     */
    @Override
    public Result deleteItemParamById(Long id) {
        Integer param = commonItemFeignClient.deleteItemParamById(id);
        if(param !=null && param>0){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    /**
     * 查询商品规格参数模板
     * @param itemCatId
     * @return
     */
    @Override
    public Result selectItemParamByItemCatId(Long itemCatId) {

        TbItemParam tbItemParam = commonItemFeignClient.selectItemParamByItemCatId(itemCatId);
        if(tbItemParam !=null){
            return Result.ok(tbItemParam);
        }

        return Result.error("查无结果");
    }
}
