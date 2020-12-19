package com.demo.service;

import com.alibaba.fastjson.JSON;
import com.demo.entity.RequestParam;
import com.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author zhengyang
 */
@Service
public class TranslationService {


    @Autowired
    private RestTemplate restTemplate;

    private final String appid = "20201217000649248";
    private final String password = "drCQotmCTKpS75B27eTd";
    private final String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    private HttpHeaders headers = new HttpHeaders();

    public Map<String, String> getResponse(RequestParam requestParam){
//        Map<String, String> requestParam1 = getRequestParam(requestParam);
//        String requestUrl = formatUrl(requestParam1, new StringBuffer(url));
//        headers.setCacheControl("no-cache");
//        headers.setConnection("keep-alive");
//        ResponseEntity<Map> exchange = restTemplate.exchange(requestUrl, HttpMethod.GET, new HttpEntity(headers), Map.class);
//        if(exchange.getStatusCode() == HttpStatus.OK){
//            Map body = exchange.getBody();
//            if(body != null){
//                return body;
//            }
//        }
        return getRequestParam(requestParam);
    }

    private Map<String,String> getRequestParam(RequestParam requestParam){
        String random = String.valueOf(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer(appid).append(requestParam.getContent()).append(random).append(password);
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("q",requestParam.getContent());
        map.put("from",requestParam.getFromLang());
        map.put("to",requestParam.getToLang());
        map.put("appid",appid);
        map.put("sign", MD5Util.md5(sb.toString()));
        return map;
    }

    public String formatUrl(Map<String,String> map,StringBuffer sb){
        sb.append("?");
        map.forEach((k, v)->{
            String value = k + "=" + v;
            sb.append(k).append("=").append(v.trim());
            sb.append("&");
        });
        return sb.substring(0,sb.length()-1);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
