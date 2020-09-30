package com.ms.music.api.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.ms.music.api.file.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileInfoController {

    private final FileInfoService fileInfoService;

    @Autowired
    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @GetMapping("/getVideoUrl/{id}")
    public JSONObject getVideoUrl(@PathVariable String id) {
        return fileInfoService.getFileInfo(id);
    }
}
