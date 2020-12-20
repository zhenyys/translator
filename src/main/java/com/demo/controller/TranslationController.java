package com.demo.controller;

import com.demo.entity.RequestParam;
import com.demo.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @RequestMapping("/getTranslationContent")
    public Map<String, String> getTranslationContent(RequestParam param){
        return translationService.restTemplateSendRequest(param);
    }

}
