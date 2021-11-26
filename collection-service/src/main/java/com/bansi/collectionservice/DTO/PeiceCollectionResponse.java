package com.bansi.collectionservice.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PeiceCollectionResponse {
    private String title;
    private List<ItemResponse> items;    
}
