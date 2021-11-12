package com.bannsi.peiceservice.repository;

import java.util.List;

import com.bannsi.peiceservice.model.Peice;

import org.springframework.data.repository.CrudRepository;

public interface PeiceRepository extends CrudRepository<Peice,Long>{
    List<Peice> findByUserId(Long userId);
}
