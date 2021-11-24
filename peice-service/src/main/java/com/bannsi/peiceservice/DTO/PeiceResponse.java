package com.bannsi.peiceservice.DTO;

import java.util.Date;
import java.util.List;

import com.bannsi.peiceservice.model.Peice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeiceResponse {
    private Long peiceId;
    private String title;
    private String content;
    private Long latitude;
    private Long longitude;
    private Date createdAt;
    private List<String> images;

    public PeiceResponse(Peice peice, List<String> images){
        this.peiceId = peice.getPeiceId();
        this.title = peice.getTitle();
        this.content = peice.getContent();
        this.latitude = peice.getLatitude();
        this.longitude = peice.getLongitude();
        this.createdAt = peice.getCreatedAt();
        this.images = images;
    }
}
