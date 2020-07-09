package com.bjsxt.common.redis.service;

import com.bjsxt.pojo.TbItem;

public interface ItemService {


     void insertItemByItemId(TbItem tbItem);

      TbItem selectItem(Long ItemId);
}
