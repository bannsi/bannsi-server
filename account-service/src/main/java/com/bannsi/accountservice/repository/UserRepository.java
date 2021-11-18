package com.bannsi.accountservice.repository;

import com.bannsi.accountservice.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String>{
}
