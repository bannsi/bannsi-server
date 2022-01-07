package com.bannsi.pieceservice.DTO;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieceRequest {
    private String title;
    @DateTimeFormat(iso = ISO.DATE)
    private Date date;
    private String content;
    private Double latitude;
    private Double longitude;
    private String address;
    private String addressDetail;
    private String placeUrl;
    private List<MultipartFile> images;
    private String keywords;
    private String whos;
}
