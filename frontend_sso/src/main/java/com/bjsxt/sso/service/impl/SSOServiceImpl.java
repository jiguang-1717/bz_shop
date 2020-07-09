package com.bjsxt.sso.service.impl;

import com.bjsxt.mapper.TbUserMapper;
import com.bjsxt.pojo.TbUser;
import com.bjsxt.pojo.TbUserExample;
import com.bjsxt.sso.feign.CommonRedisFeignClient;
import com.bjsxt.sso.service.SSOService;
import com.bjsxt.utils.MD5Utils;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Override
    public Result checkUserInfo(String checkValue, Integer checkFlag) {
        TbUserExample example=new TbUserExample();
        if(1==checkFlag){
            example.createCriteria().andUsernameEqualTo(checkValue);
        }else if(2==checkFlag){
            example.createCriteria().andPhoneEqualTo(checkValue);
        }
        long rows = tbUserMapper.countByExample(example);
        if(rows>0){
            return Result.error("数据不可用");
        }
        return Result.ok(checkValue);
    }

    /**
     * 用户注册接口
     * @param tbUser
     * @return
     */
    @Override
    public Result userRegister(TbUser tbUser) {
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUser.setPassword(MD5Utils.digest(tbUser.getPassword()));
        Integer rows = tbUserMapper.insertSelective(tbUser);
        if(1==rows){
            return Result.ok();
        }
        return Result.error("注册失败");
    }

    /**
     * 用户登录接口
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //根据用户名密码查询数据库
        TbUser tbUser=login(username,password);
        if(tbUser ==null){
            return Result.error("用户名或密码有误，请重新输入");
        }
        //将通过UUID生成token添加到Redis中
        String userToken= UUID.randomUUID().toString();
        Integer rows = insertUserToRedis(tbUser, userToken);
        if(200==rows){
            Map<String,String> map=new HashMap<>();
            map.put("token",userToken);
            map.put("userid",tbUser.getId().toString());
            map.put("username",username);
            return Result.ok(map);
        }
        return Result.error("登录失败");
    }

    /**
     * 用户登出
     * @param token
     * @return
     */
    @Override
    public Result logOut(String token) {
        try{
            commonRedisFeignClient.logOut(token);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error("登出失败");
    }

    /**
     * 将用户和userToken添加到缓存中
     * @param tbUser
     * @param userToken
     * @return
     */
    private Integer insertUserToRedis(TbUser tbUser, String userToken) {
        try{
            commonRedisFeignClient.insertUser(tbUser,userToken);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 500;
    }

    /**
     * 用户登录业务处理
     * @param username
     * @param password
     * @return
     */
    private TbUser login(String username, String password) {
        //最好别用链式的条件判断
        String pwd = MD5Utils.digest(password);
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(pwd);
        List<TbUser> list = this.tbUserMapper.selectByExample(example);
        if(list == null || list.size() <= 0){
            return null;
        }
        return list.get(0);
    }
}
