package com.bannsi.accountservice.service;

import java.util.Optional;

import com.bannsi.accountservice.model.User;
import com.bannsi.accountservice.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User CreateUser(String kakaoId, String nickname){
        Optional<User> opUser = userRepository.findByKakaoId(kakaoId);
        User user = new User();
        if(!opUser.isPresent()){
            user.withKakaoId(kakaoId).withNickname(nickname);
            userRepository.save(user);
        } else {
            user = opUser.get();
        }
        return user;
    }

    public User getUserFromId(String kakaoId) throws Exception{
        Optional<User> opUser = userRepository.findByKakaoId(kakaoId);
        if(!opUser.isPresent()){
            throw new Exception();
        }
        return opUser.get();
    }
}