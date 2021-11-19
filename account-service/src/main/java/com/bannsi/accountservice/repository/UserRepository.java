package com.bannsi.accountservice.repository;

import java.util.Optional;

import com.bannsi.accountservice.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String>{
    Optional<User> findByKakaoId(String kakaoId);
}
