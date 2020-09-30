package com.ms.music.api.file.service;

import com.alibaba.fastjson.JSONObject;
import com.ms.music.api.Utils.HttpUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FileInfoService {

    private final static String FILE_SERVER_URL = "http://192.168.3.8:9010/ms-music-file-server/";
    private final static String FILE_SERVER_FILE_INFO = "getVideoUrl";

    public JSONObject getFileInfo(String id) {
        Map<String, String> querys = new HashMap<>();
        querys.put("id", id);
        return JSONObject.parseObject(HttpUtils.sendRequest(FILE_SERVER_URL + FILE_SERVER_FILE_INFO, HttpMethod.GET, null, querys, null));
    }
}
