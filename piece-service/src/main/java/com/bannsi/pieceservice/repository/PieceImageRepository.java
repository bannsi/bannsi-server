package com.bannsi.pieceservice.repository;

import java.util.List;

import com.bannsi.pieceservice.model.PieceImage;

import org.springframework.data.repository.CrudRepository;

public interface PieceImageRepository extends CrudRepository<PieceImage, Long>{
        List<PieceImage> findByPieceId(Long pieceId);
}
