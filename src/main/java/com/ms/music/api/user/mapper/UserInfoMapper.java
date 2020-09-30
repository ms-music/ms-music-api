package com.ms.music.api.user.mapper;

import com.ms.music.api.user.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {

    UserInfo selectUserInfoByUserId(String userId);

    UserInfo login(String userName);
}
