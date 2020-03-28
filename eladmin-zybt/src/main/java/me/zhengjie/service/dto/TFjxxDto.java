package me.zhengjie.service.dto;

import lombok.Data;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
* @author zengjian
* @date 2020-03-27
*/
@Data
public class TFjxxDto implements Serializable {

    /** 防止精度丢失 */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /** 附件名称 */
    private String fjmc;

    /** 附件后缀 */
    private String fjhz;

    /** 附件大小 */
    private String fjdx;

    /** 流程id */
    private String lcid;

    /** 存放位置 */
    private String cfwz;

    /** 访问地址 */
    private String url;

    /** 申报信息id */
    private String sbxxid;

    /** 备用字段 */
    private String bak1;

    /** 备用字段 */
    private String bak2;

    /** 备用字段 */
    private String bak3;
}