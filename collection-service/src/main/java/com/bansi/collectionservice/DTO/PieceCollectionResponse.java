package com.bansi.collectionservice.DTO;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PieceCollectionResponse {
    private Long id;
    private String title;
    private String coverImage;
    private Date startDate;
    private Date endDate;
    private List<ItemResponse> items;    
}
