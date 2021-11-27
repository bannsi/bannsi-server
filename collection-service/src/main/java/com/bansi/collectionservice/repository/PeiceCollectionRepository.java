package com.bansi.collectionservice.repository;

import java.util.List;
import java.util.Optional;

import com.bansi.collectionservice.model.PeiceCollection;

import org.springframework.data.repository.CrudRepository;

public interface PeiceCollectionRepository extends CrudRepository<PeiceCollection, Long> {
    Optional<PeiceCollection> findByCollectionId(Long collectionId);
    List<PeiceCollection> findByUserId(String userId);
}
