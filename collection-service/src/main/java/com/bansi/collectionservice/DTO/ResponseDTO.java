package com.bansi.collectionservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDTO {
    private static final long serialVersionUID = 292342448923180L;
    private final String message;
    private final Object body;
}
