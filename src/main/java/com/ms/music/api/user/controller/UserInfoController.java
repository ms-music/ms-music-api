package com.ms.music.api.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.ms.music.api.user.model.UserInfo;
import com.ms.music.api.user.service.UserInfoService;
import com.ms.music.api.user.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/getUserInfoByUserId")
    public UserInfo getUserInfoByUserId() {
        return userInfoService.getUserInfoByUserId("1");
    }

    @GetMapping("/login")
    public JSONObject login(@RequestParam("user_name") String userName, @RequestParam("user_password") String userPassword) {
        return userInfoService.login(userName, userPassword);
    }
}
