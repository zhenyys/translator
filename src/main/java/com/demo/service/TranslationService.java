package com.demo.service;

import com.alibaba.fastjson.JSON;
import com.demo.entity.RequestParam;
import com.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.util.IOUtils.close;

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


    public Map<String,String> restTemplateSendRequest(RequestParam requestParam){
        Map<String, String> requestParam1 = getRequestParam(requestParam);
        String sendUrl = formatUrl(requestParam1, new StringBuffer(url));
        ResponseEntity<String> exchange = restTemplate.exchange(sendUrl, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
        if(exchange.getStatusCode() == HttpStatus.OK){
            String body = exchange.getBody();
            return JSON.parseObject(body, Map.class);
        }
        return null;
    }

    private Map<String,String> getRequestParam(RequestParam requestParam){
        String random = String.valueOf(System.currentTimeMillis());
        String sb = appid + requestParam.getContent() + random + password;
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("q",requestParam.getContent());
        map.put("from",requestParam.getFromLang());
        map.put("to",requestParam.getToLang());
        map.put("appid",appid);
        map.put("salt",random);
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

}
