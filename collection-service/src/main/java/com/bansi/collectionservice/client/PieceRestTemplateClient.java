package com.bansi.collectionservice.client;

import com.bansi.collectionservice.model.Piece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PieceRestTemplateClient {
    @Autowired
    private RestTemplate restTemplate;

    public Piece getPiece(Long pieceId){
        ResponseEntity<Piece> restExchange = restTemplate.exchange("http://pieceservice/pieces/v1/rt/{pieceId}", HttpMethod.GET, null, Piece.class, pieceId);
        return restExchange.getBody();
    }
}
