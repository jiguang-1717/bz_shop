package com.bjsxt.backend.item.service;

import com.bjsxt.utils.Result;

public interface ItemParamService {

    /**
     * 查询商品规格参数模板
     * @param itemCatId
     * @return
     */
    Result selectItemParamByItemCatId(Long itemCatId);

    /**
     * 规格参数
     * @return
     */
     Result selectItemParamAll(Integer page,Integer rows);

    /**
     * 添加规格模板
     * @param itemCatId
     * @param paramData
     * @return
     */
     Result insertItemParam(Long itemCatId,String paramData);

    /**
     * 删除参数规格模板
     * @param id
     * @return
     */
     Result deleteItemParamById(Long id);
}
