package me.zhengjie.service.dto;

import lombok.Data;

@Data
public class EnclosureParameter {

    //附件名称
    private String name;

    //附件类型
    private String type;

    //上传人id字符串
    private String idStr;
}
