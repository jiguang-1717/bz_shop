package com.bjsxt.content.service;

import com.bjsxt.utils.Result;



public interface ContentCategoryService {

    /**
     * 分类内容管理 查询
     * @param id
     * @return
     */
     Result selectContentCategoryByParentId(Long id);

    /**
     * 分类内容添加
     */
     Result insertContentCategory(Long parentId, String name);

    /**
     * 删除内容分类
     * @param categoryId
     * @return
     */
     Result deleteContentCategoryById(Long categoryId);

    /**
     * 修改内容分类
     * @param
     * @param name
     * @return
     */
     Result updateContentCategory(Long id,String name);
}
