package com.bansi.collectionservice.repository;

import java.util.List;
import java.util.Optional;

import com.bansi.collectionservice.model.PieceCollection;

import org.springframework.data.repository.CrudRepository;

public interface PieceCollectionRepository extends CrudRepository<PieceCollection, Long> {
    Optional<PieceCollection> findByCollectionId(Long collectionId);
    List<PieceCollection> findByUserId(String userId);
}
