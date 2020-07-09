package com.bjsxt.content.service.impl;

import com.bjsxt.content.feign.CommonContentFeignClient;
import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    /**
     * 内类管理 查询接口
     * @param categoryId
     * @return
     */
    @Override
    public Result selectTbContentAllByCategoryId(Long categoryId) {
        PageResult result = commonContentFeignClient.selectTbContentAllByCategoryId(categoryId);

        if(result !=null && result.getResult().size()>0){
            return Result.ok(result);
        }
        return Result.error("查无结果");
    }



    /**
     * 内容管理 添加接口
     * @param tbContent
     * @return
     */
    @Override
    public Result insertTbContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        tbContent.setPic("http://localhost:8080/contentcategory");
        Integer row = commonContentFeignClient.insertTbContent(tbContent);
        if(1==row){
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    /**
     * 内容管理 删除接口
     * @param ids
     * @return
     */
    @Override
    public Result deleteContentByIds(Long ids) {
        Integer row = commonContentFeignClient.deleteContentByIds(ids);
        if(0<row){
            return Result.ok();
        }
        return Result.error("删除失败");
    }
}
