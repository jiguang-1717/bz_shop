package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContentCategory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ContentCategoryService {

    /**
     * 查询分类的内容
     * @param
     * @return
     */
     List<TbContentCategory> selectContentCategoryByParentId(Long parentId);

    /**
     * 添加分类
     * @param tbContentCategory
     * @return
     */
     Integer insertContentCategory(TbContentCategory tbContentCategory);

    /**
     * 删除内容分类
     * @param categoryId
     * @return
     */
     Integer deleteContentCategoryById(Long categoryId);

    /**
     * 修改内容分类
     */
     Integer updateContentCategory(Long id,String name);

}
