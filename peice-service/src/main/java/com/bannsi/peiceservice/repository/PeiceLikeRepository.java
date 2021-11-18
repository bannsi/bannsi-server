package com.bannsi.peiceservice.repository;

import com.bannsi.peiceservice.model.PeiceLike;

import org.springframework.data.repository.CrudRepository;

public interface PeiceLikeRepository extends CrudRepository<PeiceLike, Long>{
    Long countByPeiceId(Long peiceId);
}
