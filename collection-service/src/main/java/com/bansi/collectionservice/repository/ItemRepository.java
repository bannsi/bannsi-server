package com.bansi.collectionservice.repository;

import com.bansi.collectionservice.model.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    
}
