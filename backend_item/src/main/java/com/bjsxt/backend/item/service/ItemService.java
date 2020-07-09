package com.bjsxt.backend.item.service;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemService {

    /**
     * 商品列表分页显示
     * @param page
     * @param rows
     * @return
     */
    Result selectTbItemAllByPage (Integer page,Integer rows);

    /**
     * 商品添加
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    Result insertTbItem(TbItem tbItem, String desc, String itemParams);

    /**
     * 商品删除
     * @param itemId
     * @return
     */
    Result deleteItemById(Long itemId);

    /**
     * 商品的预更新信息查询
     * @param itemId
     * @return
     */
    Result preUpdateItem(@RequestParam Long itemId);

    /**
     * 商品更新
     */
     Result updateTbItem(TbItem tbItem,String desc,String itemParams);
}
