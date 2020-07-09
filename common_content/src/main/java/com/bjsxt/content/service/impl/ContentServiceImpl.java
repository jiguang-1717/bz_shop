package com.bjsxt.content.service.impl;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.mapper.TbContentMapper;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.pojo.TbContentExample;
import com.bjsxt.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    /**
     * 内容管理 添加接口
     * @param tbContent
     * @return
     */
    @Override
    public Integer insertTbContent(TbContent tbContent) {

        return tbContentMapper.insertSelective(tbContent);
    }



    /**
     * 内容管理 查询接口
     * @param categoryId
     * @return
     */
    @Override
    public PageResult selectTbContentAllByCategoryId(Long categoryId) {
        PageHelper.startPage(1,20);
        TbContentExample example=new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> list= tbContentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo=new PageInfo<>(list);
        PageResult result=new PageResult();
        result.setPageIndex(1);
        result.setPageTotal(pageInfo.getTotal());
        result.setResult(list);

        return result;
    }

    /**
     * 内容管理 删除接口
     * @param ids
     * @return
     */
    @Override
    public Integer deleteContentByIds(Long ids) {

        return tbContentMapper.deleteByPrimaryKey(ids);
    }

    /**
     * 前台大广告
     * @return
     */
    @Override
    public List<Map<String, Object>> selectFrontendContentByAD() {
        TbContentExample example=new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo((long) 89);
        List<TbContent> list = tbContentMapper.selectByExample(example);
        List<Map<String,Object>> list1=new ArrayList<>();
        for(TbContent content : list){
            Map<String,Object> map=new HashMap<>();
            map.put("heightB",240);
            map.put("src",content.getPic());
            map.put("width",670);
            map.put("alt",content.getSubTitle());
            map.put("srcB",null);
            map.put("widthB",550);
            map.put("href",content.getUrl());
            map.put("height",240);
            list1.add(map);
        }
        System.out.println(list1);
        return list1;
    }
}
