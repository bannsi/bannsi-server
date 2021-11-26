package com.bansi.collectionservice.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Peice {
    private Long peiceId;
    private String title;
    private String content;
    private List<String> images;
}
