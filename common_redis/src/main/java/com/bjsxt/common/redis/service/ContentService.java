package com.bjsxt.common.redis.service;

import java.util.List;
import java.util.Map;

public interface ContentService {

     void insertContentAD(List<Map<String,Object>> list);

      List<Map<String,Object>> selectContentAD();
}
