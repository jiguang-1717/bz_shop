package com.bjsxt.search.controller;

import com.bjsxt.search.service.SolrService;
import com.bjsxt.utils.Result;
import com.bjsxt.utils.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SolrController {

    @Autowired
    private SolrService solrService;
    /**
     * 向solr中导入数据
     */
    @RequestMapping("/importAll")
    public Result importAll(){
        return solrService.importAll();
    }


    /**
     * 搜索数据
     */
    @RequestMapping("/list")
    public List<SolrDocument> selectByq(String q, @RequestParam(defaultValue = "1") Long page, @RequestParam(defaultValue = "10") Integer pageSize){
        try{
            return solrService.selectByq(q, page, pageSize);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
