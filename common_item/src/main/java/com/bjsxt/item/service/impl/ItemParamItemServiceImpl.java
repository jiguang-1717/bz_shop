package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemParamItemService;
import com.bjsxt.mapper.TbItemParamItemMapper;
import com.bjsxt.pojo.TbItemParamExample;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.pojo.TbItemParamItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public Integer updateItemParamItem(TbItemParamItem tbItemParamItem) {

        tbItemParamItem.setUpdated(new Date());
        TbItemParamItemExample example=new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(tbItemParamItem.getItemId());
        return tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem, example);
    }

    /**
     * 规格参数
     * @param itemId
     * @return
     */
    @Override
    public TbItemParamItem selectTbItemParamItemByItemId(Long itemId) {
        TbItemParamItemExample example=new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        return list.get(0);
    }

    @Override
    public Integer insertItemParamItem(TbItemParamItem tbItemParamItem) {
        return tbItemParamItemMapper.insertSelective(tbItemParamItem);
    }
}
