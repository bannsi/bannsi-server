package com.bannsi.accountservice.controller;

import java.util.HashMap;

import com.bannsi.accountservice.service.KakaoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts/v1")
public class AccountServiceController {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceController.class);

    @Autowired
    private KakaoService kakaoService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }
    
    @RequestMapping(value = "/kakao/authCode", method = RequestMethod.GET)
    public String kakaoAuthCode(){
        String authCode = kakaoService.getAuthCode();
        return authCode;
    }

    @RequestMapping(value = "/kakao/login/", method = RequestMethod.GET)
    public String kakaoLogin(String code, Model model) throws Exception {
        logger.info("Authorization Code = " + code);
        String accessToken = kakaoService.getAccessToken(code);
        logger.info("Access Token: " + accessToken);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        logger.info("nickname: " + String.valueOf(userInfo.get("nickname")));
        // model.addAttribute("kakaoInfo", kakaoInfo);
        return "/home";
    }
}
