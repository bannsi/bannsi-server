package com.bannsi.accountservice.controller;

import com.bannsi.accountservice.DTO.UserResponse;
import com.bannsi.accountservice.model.User;
import com.bannsi.accountservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "accounts/v1/rt")
public class AccountRestTemplateController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{kakaoId}", method = RequestMethod.GET)
    public UserResponse getUser(@PathVariable String kakaoId) throws Exception {
        User user = userService.getUserFromId(kakaoId);
        UserResponse userResponse = new UserResponse();
        userResponse.setNickname(user.getNickname());
        return userResponse;
    }
}
