package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemParamService;
import com.bjsxt.mapper.TbItemParamMapper;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.pojo.TbItemParamExample;
import com.bjsxt.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;


    /**
     * 商品规格参数模板查询
     * @param itemCatId
     * @return
     */
    @Override
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {

        TbItemParamExample example=new TbItemParamExample();
        example.createCriteria().andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if(list !=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询规格参数模板
     * @return
     */
    @Override
    public PageResult selectItemParamAll(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        TbItemParamExample example=new TbItemParamExample();
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbItemParam> pageInfo=new PageInfo<>(list);
        PageResult result=new PageResult();
        result.setPageIndex(page);
        result.setPageTotal(pageInfo.getTotal());
        result.setResult(list);

        return result;
    }

    /**
     * 添加规格参数
     * @param tbItemParam
     * @return
     */
    @Override
    public Integer insertItemParam(TbItemParam tbItemParam) {

        return  tbItemParamMapper.insertSelective(tbItemParam);

    }

    /**
     * 删除参数规格模板
     * @param id
     * @return
     */
    @Override
    public Integer deleteItemParamById(Long id) {

        return tbItemParamMapper.deleteByPrimaryKey(id);
    }
}
