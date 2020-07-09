package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemDescService;
import com.bjsxt.mapper.TbItemDescMapper;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemDescExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public Integer insertItemDesc(TbItemDesc tbItemDesc) {

        return tbItemDescMapper.insertSelective(tbItemDesc);
    }


    @Override

    public Integer updateTbItemDesc(TbItemDesc tbItemDesc) {
        tbItemDesc.setUpdated(new Date());
        return tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
    }

    /**
     * 前台商品介绍查询
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc selectItemDescByItemId(Long itemId) {

        TbItemDescExample example=new TbItemDescExample();
        example.createCriteria().andItemIdEqualTo(itemId);
        List<TbItemDesc> list = tbItemDescMapper.selectByExampleWithBLOBs(example);

        return list.get(0);
    }
}
