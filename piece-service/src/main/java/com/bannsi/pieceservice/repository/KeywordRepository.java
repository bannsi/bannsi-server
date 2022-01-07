package com.bannsi.pieceservice.repository;

import java.util.List;

import com.bannsi.pieceservice.model.Keyword;

import org.springframework.data.repository.CrudRepository;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {
    List<Keyword> findAll();
}
