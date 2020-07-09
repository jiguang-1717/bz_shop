package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 查询商品规格参数模板接口
 */

public interface ItemParamService {

    TbItemParam selectItemParamByItemCatId(Long itemCatId);

    /**
     * 查询规格参数
     * @return
     */
     PageResult selectItemParamAll(Integer page,Integer rows);

    /**
     * 添加规格参数
     */
     Integer insertItemParam(TbItemParam tbItemParam);

    /**
     * 删除规格模板
     */
     Integer deleteItemParamById(Long id);

}
