package com.bannsi.peiceservice.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 2935848923180L;
    private final String message;
    private final Object body;
}
