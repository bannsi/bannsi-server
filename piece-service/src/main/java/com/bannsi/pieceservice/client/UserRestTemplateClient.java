package com.bannsi.pieceservice.client;

import com.bannsi.pieceservice.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserRestTemplateClient {
    @Autowired
    private RestTemplate restTemplate;

    public User getUser(String kakaoId){
        ResponseEntity<User> restExchange = restTemplate.exchange("http://accountservice/accounts/v1/rt/{kakaoId}", HttpMethod.GET, null, User.class, kakaoId);
        return restExchange.getBody();
    }
}
