package com.bansi.collectionservice.DTO;

import java.util.Date;

import com.bansi.collectionservice.model.Peice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemResponse {
    private String content;
    private Peice peice;
    private Integer orderNum;
    private Date date;    
}
