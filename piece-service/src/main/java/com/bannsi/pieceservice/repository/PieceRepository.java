package com.bannsi.pieceservice.repository;

import java.util.List;

import com.bannsi.pieceservice.model.Piece;

import org.springframework.data.repository.CrudRepository;

public interface PieceRepository extends CrudRepository<Piece,Long>{
    List<Piece> findByUserId(String userId);
}
