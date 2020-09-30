package com.ms.music.api.Utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public final class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static final RestTemplate restTemplate;

    static {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(3000);
        simpleClientHttpRequestFactory.setReadTimeout(3000);
        restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
    }

    private static String getUtlFromQuerys(String url, Map<String, String> querys) {
        StringBuilder urlStringBuilder = new StringBuilder(url);
        urlStringBuilder.append("?");
        for (String key : querys.keySet()) {
            urlStringBuilder.append(key);
            urlStringBuilder.append("=");
            urlStringBuilder.append(querys.get(key));
            urlStringBuilder.append("&");
        }
        return urlStringBuilder.substring(0, urlStringBuilder.length() - 1);
    }

    public static String sendRequest(String url, HttpMethod method, HttpHeaders headers, Map<String, String> querys, Object body) {
        if (StringUtils.isEmpty(url) || method == null) {
            return null;
        }
        if (method == HttpMethod.GET || method == HttpMethod.DELETE) {
            body = null;
        }
        if (headers == null) {
            headers = new HttpHeaders();
        }
        if (!headers.containsKey(HttpHeaders.CONTENT_TYPE)) {
            headers.set("Content-Type", "application/json;charset=UTF-8");
        }
        if (querys != null && querys.size() > 0) {
            url = getUtlFromQuerys(url, querys);
        }
        MultiValueMap<String, Object> multiValueMap = null;
        if (body instanceof JSONObject) {
            MediaType contentType = headers.getContentType();
            if (contentType != null && (contentType.toString().contains("application/x-www-form-urlencoded") || contentType.toString().contains("multipart/form-data"))) {
                JSONObject bodyJson = (JSONObject) body;
                multiValueMap = new LinkedMultiValueMap<>(bodyJson.size());
                for (String key : bodyJson.keySet()) {
                    multiValueMap.add(key, bodyJson.get(key));
                }
            }
        }
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, new HttpEntity<>(multiValueMap != null ? multiValueMap : body, headers), String.class);
            String result = responseEntity.getBody();
            logger.info("Http请求url：" + url);
            logger.info("Http请求method：" + method);
            logger.info("Http请求headers：" + headers.toString());
            logger.info("Http请求querys：" + (querys != null ? querys.toString() : "{}"));
            String re;
            if (multiValueMap != null) {
                re = multiValueMap.toString();
            } else if (body != null) {
                re = JSONObject.toJSONString(body);
            } else {
                re = "{}";
            }
            logger.info("Http请求body：" + re);
            logger.info("Http请求结果：" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
