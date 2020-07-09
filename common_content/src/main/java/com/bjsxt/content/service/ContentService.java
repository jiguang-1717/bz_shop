package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface ContentService {

    /**
     * 内容管理 查询接口
     * @param categoryId
     * @return
     */
     PageResult selectTbContentAllByCategoryId(Long categoryId);

    /**
     * 内容管理 添加接口
     * @param tbContent
     * @return
     */
     Integer insertTbContent(TbContent tbContent);

    /**
     * 内容管理 删除接口
     * @param ids
     * @return
     */
     Integer deleteContentByIds(Long ids);

    /**
     * 前台大广告位
     * @return
     */
     List<Map<String,Object>> selectFrontendContentByAD();
}
