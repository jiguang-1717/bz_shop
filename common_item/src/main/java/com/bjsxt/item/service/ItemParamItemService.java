package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemParamItem;

public interface ItemParamItemService {

    Integer insertItemParamItem(TbItemParamItem tbItemParamItem);

    Integer updateItemParamItem(TbItemParamItem tbItemParamItem);

     TbItemParamItem selectTbItemParamItemByItemId(Long itemId);
}
