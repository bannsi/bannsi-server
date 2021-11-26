package com.bansi.collectionservice.repository;

import java.util.Optional;

import com.bansi.collectionservice.model.PeiceCollection;

import org.springframework.data.repository.CrudRepository;

public interface PeiceCollectionRepository extends CrudRepository<PeiceCollection, Long> {
    Optional<PeiceCollection> findByCollectionId(Long collectionId);
}
