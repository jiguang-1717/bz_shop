package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.RequestBody;

public interface ContentService {

    /**
     * 内容管理 查询接口
     * @param categoryId
     * @return
     */
     Result selectTbContentAllByCategoryId(Long categoryId);

    /**
     * 内容管理 添加接口
     * @param tbContent
     * @return
     */
     Result insertTbContent(TbContent tbContent);

    /**
     * 内容管理 删除接口
     * @param ids
     * @return
     */
     Result deleteContentByIds(Long ids);
}
