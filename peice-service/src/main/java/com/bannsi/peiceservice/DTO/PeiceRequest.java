package com.bannsi.peiceservice.DTO;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeiceRequest {
    private String title;
    @DateTimeFormat(iso = ISO.DATE)
    private Date date;
    private String content;
    private Long latitude;
    private Long longitude;
    private String address;
    private String addressDetail;
    private String placeUrl;
    private List<MultipartFile> images;
    private List<Long> keywords;
    private List<Long> whos;
}
