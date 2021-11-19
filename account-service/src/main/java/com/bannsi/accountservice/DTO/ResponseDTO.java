package com.bannsi.accountservice.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDTO implements Serializable {
    private static final long serialVersionUID = 2031882923844541L;
    private final String message;
    private final Object body;
}
