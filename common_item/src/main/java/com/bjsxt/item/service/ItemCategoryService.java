package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.utils.CatResult;

import java.util.List;

/**
 * 商品类目查询接口
 */
public interface ItemCategoryService {


    /**
     *商品类目查询
     * @param id
     * @return
     */
    List<TbItemCat> selectItemCategoryByParentId(Long id);

    /**
     * 前台左侧商品分类显示
     * @return
     */
     CatResult selectItemCategoryAll();
}
