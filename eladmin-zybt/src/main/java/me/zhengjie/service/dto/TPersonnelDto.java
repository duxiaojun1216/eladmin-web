package me.zhengjie.service.dto;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author zengjina
* @date 2020-03-21
*/
@Data
public class TPersonnelDto implements Serializable {

    private Long id;


    /** 电话 */
    private String phone;

    /** 身份类型 */
    private String type;


    /** 创建人Id */
    private String createid;


    /** 备注 */
    private String mark;

    /** 状态（是否启用） */
    private String state;

    /** 备用字段2 */
    private String bak2;

    /** 备用字段3 */
    private String bak3;

    /** 备用字段4 */
    private String bak4;

    /** 备用字段5 */
    private String bak5;

    /** 姓名 */
    private String tname;

    private String cardId;

    private Timestamp createTime;

    private String updateId;

    private Timestamp updateTime;

    //户籍地址
    private  String hjdz;

}