package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemDesc;



public interface ItemDescService {

    Integer insertItemDesc(TbItemDesc tbItemDesc);

    Integer updateTbItemDesc(TbItemDesc tbItemDesc);

     TbItemDesc selectItemDescByItemId(Long itemId);
}
