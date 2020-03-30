package me.zhengjie.service.dto;

import lombok.Data;

@Data
public class Result {

    private Boolean success=false;
    private String code;
    private long sbxxid;
    private String message;
}
