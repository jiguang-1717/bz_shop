package com.bjsxt.content.service.impl;

import com.bjsxt.content.feign.CommonContentFeignClient;
import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;



    /**
     * 分类内容管理 查询
     * @param id
     * @return
     */
    @Override
    public Result selectContentCategoryByParentId(Long id) {
        List<TbContentCategory> list = commonContentFeignClient.selectContentCategoryByParentId(id);
        if(!CollectionUtils.isEmpty(list)){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }

    /**
     * 分类内容添加
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public Result insertContentCategory(Long parentId, String name) {
        TbContentCategory tbContentCategory=new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        Integer contentCategory = commonContentFeignClient.insertContentCategory(tbContentCategory);
        if(1==contentCategory){
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    /**
     * 删除内容分类
     * @param categoryId
     * @return
     */
    @Override
    public Result deleteContentCategoryById(Long categoryId) {
        Integer row = commonContentFeignClient.deleteContentCategoryById(categoryId);
        if(200==row){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    /**
     * 修改内容分类
     * @param
     * @param name
     * @return
     */
    @Override
    public Result updateContentCategory(Long id, String name) {
        Integer row = commonContentFeignClient.updateContentCategory(id, name);
        if(row>0){
            return Result.ok();
        }
        return Result.error("修改失败");
    }
}
