package com.bannsi.peiceservice.repository;

import java.util.List;

import com.bannsi.peiceservice.model.Keyword;

import org.springframework.data.repository.CrudRepository;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {
    List<Keyword> findAll();
}
