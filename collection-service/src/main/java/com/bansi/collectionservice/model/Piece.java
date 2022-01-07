package com.bansi.collectionservice.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Piece {
    private Long pieceId;
    private String content;
    private Long latitude;
    private Long longitude;
    private String address;
    private String addressDetail;
    private String placeUrl;
    private Date date;
    private List<String> images;
    private List<String> keywords;
    private List<String> whos;
}
