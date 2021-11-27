package com.bannsi.peiceservice.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bannsi.peiceservice.model.Keyword;
import com.bannsi.peiceservice.model.Peice;
import com.bannsi.peiceservice.model.User;
import com.bannsi.peiceservice.model.WhoKeyword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeiceResponse {
    private Long peiceId;
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

    public PeiceResponse(Peice peice, User user, List<String> images, List<Keyword> keywords, List<WhoKeyword> whos){
        this.peiceId = peice.getPeiceId();
        this.user = user;
        this.content = peice.getContent();
        this.latitude = peice.getLatitude();
        this.longitude = peice.getLongitude();
        this.date = peice.getDate();
        this.address = peice.getAddress();
        this.addressDetail = peice.getAddressDetail();
        this.placeUrl = peice.getPlaceUrl();
        this.createdAt = peice.getCreatedAt();
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
