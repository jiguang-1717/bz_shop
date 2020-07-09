package com.bjsxt.common.redis.service;

import com.bjsxt.pojo.TbUser;
import com.bjsxt.utils.CartItem;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface SSOService {

     void insertUser( TbUser tbUser,  String userToken);

      void logOut(String userToken);

}
