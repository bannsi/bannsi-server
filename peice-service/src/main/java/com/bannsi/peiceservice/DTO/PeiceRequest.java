package com.bannsi.peiceservice.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeiceRequest {
    private String title;
    private String content;
    private Long latitude;
    private Long longitude;
    private List<MultipartFile> images;
}
