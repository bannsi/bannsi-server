package com.bansi.collectionservice.DTO;

import java.util.Date;

import com.bansi.collectionservice.model.Peice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemWithPeice {
    private String content;
    private Peice peice;
    private Integer orderNumm;
    private Date date;
}
