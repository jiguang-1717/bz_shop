package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


/**
 * 商品查询接口
 */
public interface ItemService {

    /**
     * 查询商品列表，并分页显示
     * @return
     */
    PageResult selectTbItemAllByPage(Integer page,Integer rows);

    /**
     * 添加商品
     * @param tbItem
     * @return
     */
    Integer insertItem(TbItem tbItem);

    /**
     * 商品的删除与更新
     * @param tbItem
     * @return
     */
    Integer updateItem(TbItem tbItem);

    /**
     * 商品的预更新查询
     * @param itemId
     * @return
     */
    Map<String,Object> preUpdateItem(Long itemId);


    /**
     * 根据商品id查询商品详情
     * @param itemId
     * @return
     */
     TbItem selectItemInfo(Long itemId);


}
