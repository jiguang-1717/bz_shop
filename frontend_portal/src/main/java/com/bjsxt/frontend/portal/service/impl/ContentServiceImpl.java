package com.bjsxt.frontend.portal.service.impl;

import com.bjsxt.frontend.portal.feign.CommonContentFeignClient;
import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.portal.service.ContentService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    /**
     * 前台大广告位
     * @return
     */
    @Override
    public Result selectFrontendContentByAD() {
        //查询缓存
        try{
            List<Map<String, Object>> list = commonRedisFeignClient.selectContentAD();
            if(list !=null){

                return Result.ok(list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        List<Map<String, Object>> list = commonContentFeignClient.selectFrontendContentByAD();
        if(list !=null){
            try{
                commonRedisFeignClient.insertContentAD(list);

            }catch (Exception e){
                e.printStackTrace();
            }

            return Result.ok(list);
        }
        return Result.error("查无结果");
    }
}
