package com.bannsi.peiceservice.repository;

import java.util.Optional;

import com.bannsi.peiceservice.model.WhoKeyword;

import org.springframework.data.repository.CrudRepository;

public interface WhoKeywordRepository extends CrudRepository<WhoKeyword, Long> {
    Optional<WhoKeyword> findById(Long whoId);
}
