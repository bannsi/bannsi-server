package com.bansi.collectionservice.DTO;

import java.util.Date;
import java.util.List;

import com.bansi.collectionservice.model.Item;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeiceCollectionRequest {
    private String title;
    // private MultipartFile coverImage;
    private String coverImage;
    private List<Item> items;
    @DateTimeFormat(iso = ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso = ISO.DATE)
    private Date endDate;
}
