package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemService;
import com.bjsxt.mapper.TbItemCatMapper;
import com.bjsxt.mapper.TbItemDescMapper;
import com.bjsxt.mapper.TbItemMapper;
import com.bjsxt.mapper.TbItemParamItemMapper;
import com.bjsxt.pojo.*;
import com.bjsxt.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    /**
     * 查询商品列表，并分页显示
     * @return
     */
    @Override
    public PageResult selectTbItemAllByPage(Integer page,Integer rows) {

        PageHelper.startPage(page,rows);
        TbItemExample example=new TbItemExample();
        example.createCriteria().andStatusEqualTo((byte)1);
        List<TbItem> list=tbItemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        PageResult result=new PageResult();
        result.setPageIndex(page);
        result.setPageTotal(pageInfo.getTotal());
        result.setResult(list);
        return result;

    }

    /**
     * 添加商品
     * @param tbItem
     * @return
     */
    @Override
    public Integer insertItem(TbItem tbItem) {

        return tbItemMapper.insertSelective(tbItem);
    }

    /**
     * 删除商品
     * 就是将statue更改为3
     * @param tbItem
     * @return
     */
    @Override
    public Integer updateItem(TbItem tbItem) {
        tbItem.setUpdated(new Date());
        return tbItemMapper.updateByPrimaryKeySelective(tbItem);
    }

    /**
     * 商品的预更新
     * @param itemId
     * @return
     */
    @Override
    public Map<String, Object> preUpdateItem(Long itemId) {
        Map<String,Object> map = new HashMap<>();
        //根据商品ID查询商品
        TbItem item = this.tbItemMapper.selectByPrimaryKey(itemId);
        map.put("item",item);

        //根据商品ID查询商品描述
        TbItemDesc itemDesc = this.tbItemDescMapper.selectByPrimaryKey(itemId);
        map.put("itemDesc",itemDesc.getItemDesc());

        //根据商品ID查询商品类目
        TbItemCat itemCat = this.tbItemCatMapper.selectByPrimaryKey(item.getCid());
        map.put("itemCat",itemCat.getName());

        //根据商品ID查询商品规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = this.tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list != null && list.size() > 0){
            map.put("itemParamItem",list.get(0));
        }

        return map;
    }

    /**
     * 根据商品id查询商品详情
     * @param itemId
     * @return
     */
    @Override
    public TbItem selectItemInfo(Long itemId) {

        return tbItemMapper.selectByPrimaryKey(itemId);
    }


}
