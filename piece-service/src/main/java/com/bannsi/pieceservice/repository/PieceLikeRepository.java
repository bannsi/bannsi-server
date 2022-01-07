package com.bannsi.pieceservice.repository;

import com.bannsi.pieceservice.model.PieceLike;

import org.springframework.data.repository.CrudRepository;

public interface PieceLikeRepository extends CrudRepository<PieceLike, Long>{
    Long countByPieceId(Long pieceId);
}
