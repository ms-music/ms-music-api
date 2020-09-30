package com.ms.music.api.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ms.music.api.user.model.UserInfo;
import com.ms.music.api.user.service.UserInfoService;
import com.ms.music.api.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfo getUserInfoByUserId(String userId) {
        return userInfoMapper.selectUserInfoByUserId(userId);
    }

    @Override
    public JSONObject login(String userName, String userPassword) {
        if (StringUtils.isEmpty(userName)) {
            return new JSONObject().fluentPut("msg", "请输入用户名").fluentPut("status", "success");
        }
        if (StringUtils.isEmpty(userPassword)) {
            return new JSONObject().fluentPut("msg", "请输入密码").fluentPut("status", "success");
        }
        UserInfo userInfo = userInfoMapper.login(userName);
        System.out.println(JSONObject.toJSONString(userInfo));
        JSONObject reJson = new JSONObject();
        if (userInfo == null || userInfo.getUserStatus() == -1) {
            reJson.fluentPut("msg", "用户不存在").fluentPut("status", "success");
        } else if (userInfo.getUserStatus() == 0) {
            reJson.fluentPut("msg", "您已被封禁，请联系管理员！").fluentPut("status", "success");
        } else if (userInfo.getUserPassword().equals(userPassword)) {
            reJson.fluentPut("msg", "登录成功").fluentPut("status", "success");
        } else if (!userInfo.getUserPassword().equals(userPassword)) {
            reJson.fluentPut("msg", "密码错误").fluentPut("status", "success");
        }
        return reJson;
    }
}
