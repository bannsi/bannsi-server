package com.bannsi.accountservice.controller;

import java.util.HashMap;

import com.bannsi.accountservice.DTO.ResponseDTO;
import com.bannsi.accountservice.config.JwtUtil;
import com.bannsi.accountservice.model.User;
import com.bannsi.accountservice.service.KakaoService;
import com.bannsi.accountservice.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/accounts/v1")
public class AccountServiceController {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceController.class);

    @Autowired
    private KakaoService kakaoService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

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
    public ResponseEntity<?> kakaoLogin(String code, Model model) throws Exception {
        logger.info("Authorization Code = " + code);
        String accessToken = kakaoService.getAccessToken(code);
        logger.info("Access Token: " + accessToken);
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        logger.info("nickname: " + String.valueOf(userInfo.get("nickname")));

        User user = userService.CreateUser(
            String.valueOf(userInfo.get("userId")), 
            String.valueOf(userInfo.get("nickname")));
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok().body(new ResponseDTO("user created", token));
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<?> getMeInfo(@RequestHeader HttpHeaders headers) throws Exception {
        String token = headers.getFirst("Authorization").substring(7);
        String kakaoId = jwtUtil.getUsernameFromToken(token);
        User user = userService.getUserFromId(kakaoId);
        return ResponseEntity.ok().body(new ResponseDTO("get user info", user));
    }

    @RequestMapping(value="/me/", method=RequestMethod.PUT)
    public ResponseEntity<?> requestMethodName(@RequestHeader HttpHeaders headers, @RequestBody User newUser) throws Exception{
        String token = headers.getFirst("Authorization").substring(7);
        String kakaoId = jwtUtil.getUsernameFromToken(token);
        User user = userService.updateUser(kakaoId, newUser);
        return ResponseEntity.ok().body(new ResponseDTO("user nickcname chnaged", user));
    }
    
}
