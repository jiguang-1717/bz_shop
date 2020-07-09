package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemCategoryService;
import com.bjsxt.mapper.TbItemCatMapper;
import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.pojo.TbItemCatExample;
import com.bjsxt.utils.CatNode;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    /**
     * 商品类目查询
     * @param id
     * @return
     */
    @Override
    public List<TbItemCat> selectItemCategoryByParentId(Long id) {

        TbItemCatExample example=new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(id);
        example.createCriteria().andStatusEqualTo(1);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);

        return list;
    }

    /**
     * 前台左侧商品分类
     * @return
     */
    @Override
    public CatResult selectItemCategoryAll() {
//        CatResult catResult=new CatResult();
////        //查询商品分类
////        catResult.setData(getCatList(0L));
////        return catResult;
        //创建一个分类的接受集（集合）
        CatResult catResult=new CatResult();
        catResult.setData(getCatList(0L));
        return catResult;


    }

    /**
     * 查询商品分类
     * @param
     * @return
     */
    private List<?> getCatList(Long parentId) {
        //创建查询条件
        TbItemCatExample example=new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        //先查出所有的顶级分类
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);

        List resultList=new ArrayList<>();
        int count=0;
        for(TbItemCat tbItemCat : list){
            //判断是否为父节点
            if(1==tbItemCat.getIsParent()){
                CatNode catNode=new CatNode();
                catNode.setName(tbItemCat.getName());
                catNode.setItem(getCatList(tbItemCat.getId()));
                resultList.add(catNode);

                if(18==count){
                    break;
                }
            }else{
                resultList.add(tbItemCat.getName());
            }
        }
        return resultList;
    }
}
