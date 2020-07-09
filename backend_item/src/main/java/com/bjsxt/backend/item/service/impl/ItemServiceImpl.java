package com.bjsxt.backend.item.service.impl;

import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.backend.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 商品管理
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    /**
     * 商品的更新
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @Override
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams) {
        //更新TbItem
        commonItemFeignClient.updateTbItem(tbItem);
        //更新TbItemDesc
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        commonItemFeignClient.updateTbItemDesc(tbItemDesc);
        //更新TbItemParamItem
        TbItemParamItem tbItemParamItem =new TbItemParamItem();
        tbItemParamItem.setItemId(tbItem.getId());
        tbItemParamItem.setParamData(itemParams);
        commonItemFeignClient.updateItemParamItem(tbItemParamItem);
        return Result.ok();
    }

    /**
     * 商品列表分页显示
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Result selectTbItemAllByPage(Integer page, Integer rows) {
        PageResult pageResult=this.commonItemFeignClient.selectTbItemAllByPage(page,rows);
        if(pageResult !=null && pageResult.getResult() !=null){
            return Result.ok(pageResult);
        }
        return Result.error("查无数据！");
    }

    /**
     * 添加商品详情
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @Override
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams) {
        //补齐TbItem
        Long itemId= IDUtils.genItemId();
        Date date=new Date();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)1);
        tbItem.setImage("http://image.egou.com/jd/4ef8861cf6854de9889f3db9b24dc371.jpg");
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        commonItemFeignClient.insertItem(tbItem);

        //补齐TbItemDesc
        TbItemDesc tbItemDesc=new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        System.out.println(tbItemDesc);
        commonItemFeignClient.insertItemDesc(tbItemDesc);

        //补齐TbItemParamItem
        TbItemParamItem tbItemParamItem=new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        System.out.println(tbItemParamItem);
        commonItemFeignClient.insertItemParamItem(tbItemParamItem);


        return Result.ok();
    }

    /**
     * 商品的删除
     * @param itemId
     * @return
     */
    @Override
    public Result deleteItemById(Long itemId) {
        TbItem tbItem=new TbItem();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)3);
        Integer rows = commonItemFeignClient.deleteItemById(tbItem);
        if(rows !=null){
            return Result.ok();
        }
        return Result.error("查无数据！");
    }

    /**
     * 商品的预更新信息查询
     * @param itemId
     * @return
     */
    @Override
    public Result preUpdateItem(Long itemId) {

        Map<String, Object> map = commonItemFeignClient.preUpdateItem(itemId);
        if(map !=null && map.size()>0){
            return Result.ok(map);
        }
        return Result.error("查无数据！");
    }
}
