package com.bannsi.pieceservice.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bannsi.pieceservice.model.Keyword;
import com.bannsi.pieceservice.model.Piece;
import com.bannsi.pieceservice.model.User;
import com.bannsi.pieceservice.model.WhoKeyword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieceResponse {
    private Long pieceId;
    private User user;
    private String content;
    private Double latitude;
    private Double longitude;
    private String address;
    private String addressDetail;
    private String placeUrl;
    private Date date;
    private Date createdAt;
    private List<String> images;
    private List<String> keywords;
    private List<String> whos;

    public PieceResponse(Piece piece, User user, List<String> images, List<Keyword> keywords, List<WhoKeyword> whos){
        this.pieceId = piece.getPieceId();
        this.user = user;
        this.content = piece.getContent();
        this.latitude = piece.getLatitude();
        this.longitude = piece.getLongitude();
        this.date = piece.getDate();
        this.address = piece.getAddress();
        this.addressDetail = piece.getAddressDetail();
        this.placeUrl = piece.getPlaceUrl();
        this.createdAt = piece.getCreatedAt();
        this.keywords = new ArrayList<String>();
        for(Keyword keyword : keywords){
            this.keywords.add(keyword.getName());
        }
        this.whos = new ArrayList<String>();
        for(WhoKeyword whoKeyword : whos){
            this.whos.add(whoKeyword.getWho());
        }
        this.images = images;
    }
}
