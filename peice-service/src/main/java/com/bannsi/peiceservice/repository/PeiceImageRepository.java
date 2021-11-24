package com.bannsi.peiceservice.repository;

import java.util.List;

import com.bannsi.peiceservice.model.PeiceImage;

import org.springframework.data.repository.CrudRepository;

public interface PeiceImageRepository extends CrudRepository<PeiceImage, Long>{
        List<PeiceImage> findByPeiceId(Long peiceId);
}
