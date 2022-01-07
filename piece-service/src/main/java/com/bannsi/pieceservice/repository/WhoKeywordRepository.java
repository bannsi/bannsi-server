package com.bannsi.pieceservice.repository;

import java.util.Optional;

import com.bannsi.pieceservice.model.WhoKeyword;

import org.springframework.data.repository.CrudRepository;

public interface WhoKeywordRepository extends CrudRepository<WhoKeyword, Long> {
    Optional<WhoKeyword> findById(Long whoId);
}
