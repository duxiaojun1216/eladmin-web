package me.zhengjie.service.dto;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;

//审核信息汇总
@Data
public class ShxxHz {
    //人员信息
    /** 电话 */
    private String phone;

    /** 身份类型 */
    private String sqrlx;

    /** 姓名 */
    private String tname;

    /** 身份证号 */
    private String cardId;


    //房产信息
    /** 房产类型 */
    private String fclx;
    /** 面积 */
    private String fcmj;

    /** 所处位置 */
    private String scwz;




    //申报信息
    /** 申报id */
    private Long sbid;
    /** 申报人id */
    private Long sbrid;

    /** 房产id */
    private Long fcid;

    /** 购房日期 */
    private Timestamp gfrq;

    /**完签日期 */
    private Timestamp wqrq;

    /** 完税日期 */
    private Timestamp wsrq;

    /** 房产金额 */
    private String fcje;


    /** 补贴金额 */
    private String btje;

    /** 补贴余额 */
    private String btye;

}
