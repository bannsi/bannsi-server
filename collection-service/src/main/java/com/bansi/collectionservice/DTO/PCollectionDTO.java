package com.bansi.collectionservice.DTO;

import java.util.List;

import com.bansi.collectionservice.model.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PCollectionDTO {
    private String title;
    private List<Item> items;
}
