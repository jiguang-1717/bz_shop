package com.bjsxt.content.service.impl;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.mapper.TbContentCategoryMapper;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;


    /**
     * 添加分类
     * @param tbContentCategory
     * @return
     */
    @Override
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {
        //补齐TbContentCategory
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent((byte)0);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        //插入数据
        Integer contentCategoryNum = tbContentCategoryMapper.insertSelective(tbContentCategory);
        //判断添加的节点的父节点是否为子节点
        TbContentCategory tbContentCategory1 = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        if(0==tbContentCategory1.getIsParent()){
            tbContentCategory1.setIsParent((byte)1);
            tbContentCategory1.setUpdated(new Date());
            tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory1);
        }

        return contentCategoryNum;
    }

    /**
     * 查询分类
     * @param parentId
     * @return
     */
    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long parentId) {

        TbContentCategoryExample example=new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);

        return tbContentCategoryMapper.selectByExample(example);
    }

    /**
     * 修改内容分类
     * @param
     * @return
     */
    @Override
    public Integer updateContentCategory(Long id,String name) {
        //先查寻除要更新的分类内容对象
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        tbContentCategory.setName(name);
        tbContentCategory.setUpdated(new Date());
        return tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }

    /**
     * 删除内容分类
     * @param categoryId
     * @return
     */
    @Override
    public Integer deleteContentCategoryById(Long categoryId) {

        //查询当前节点
        TbContentCategory currentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(categoryId);

        //删除当前节点的子节点
        Integer status = this.deleteNode(currentCategory);

        //查询当前节点的父节点
        TbContentCategory parentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(currentCategory.getParentId());

        //查看当前节点是否有兄弟节点，决定是否修改父节点的状态。
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentCategory.getId());

        List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
        if(list.size() == 0){
            parentCategory.setIsParent((byte) 0);
            parentCategory.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKey(parentCategory);
        }

        return 200;
    }

    private Integer deleteNode(TbContentCategory currentCategory){

        if(1==currentCategory.getIsParent()){
            //是父节点
            //查询当前节点所有的子节点
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(currentCategory.getId());
            List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
            for(TbContentCategory children :list){
                this.deleteNode(children);
                this.tbContentCategoryMapper.deleteByPrimaryKey(currentCategory.getId());
            }
        }else{
            //不是父节点
            this.tbContentCategoryMapper.deleteByPrimaryKey(currentCategory.getId());
        }
        return 200;
    }




}
