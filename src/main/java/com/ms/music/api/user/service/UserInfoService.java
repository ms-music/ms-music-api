package com.ms.music.api.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ms.music.api.user.model.UserInfo;

public interface UserInfoService {

    UserInfo getUserInfoByUserId(String userId);

    JSONObject login(String userName, String userPassword);
}
