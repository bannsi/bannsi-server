package com.bannsi.pieceservice.controller;

import java.io.IOException;

import com.bannsi.pieceservice.DTO.PieceResponse;
import com.bannsi.pieceservice.client.UserRestTemplateClient;
import com.bannsi.pieceservice.model.Piece;
import com.bannsi.pieceservice.model.User;
import com.bannsi.pieceservice.service.ImageService;
import com.bannsi.pieceservice.service.PieceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "pieces/v1/rt")
public class PieceRestTemplateController {
    @Autowired
    private PieceService pieceService;

    @Autowired
    private ImageService imageService;
    
    @Autowired
    private UserRestTemplateClient userRestTemplateClient;

    // TODO: restTemplate get Piece from pieceId
    @RequestMapping(value = "/{pieceId}", method = RequestMethod.GET)
    public PieceResponse getPiece(@PathVariable Long pieceId) throws IOException {
        Piece piece =  pieceService.getPieceByPieceId(pieceId);
        User  user = userRestTemplateClient.getUser(piece.getUserId());
        return new PieceResponse(piece, user, imageService.getImageUrl(piece.getPieceId()), piece.getKeywords(), piece.getWhos());
    }
}
