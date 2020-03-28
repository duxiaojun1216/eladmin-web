package me.zhengjie.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author zengjian
* @date 2020-03-25
*/
@Data
public class TShenbaoxingxiDto implements Serializable {

    private Long id;

    /** 申报人id */
    private Long sbrid;

    /** 房产id */
    private Long fcid;

    /** 合同时间 */
    private Timestamp htsj;

    /** 网签时间 */
    private Timestamp wqsj;

    /** 房产金额 */
    private String fcje;

    /** 房产类型 */
    private String fclx;

    /** 补贴金额 */
    private String btje;

    /** 补贴余额 */
    private String btye;

    /** 申请人类型 */
    private String sqrlx;

    /** 备用 */
    private String bak2;

    /** 备用 */
    private String bak3;

    /** 备用 */
    private String bak4;

    /** 备用 */
    private String bak5;

    /** 创建人 */
    private String createId;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改人 */
    private String updateId;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 住建复核状态 */
    private String zjfh;

    /** 批次号 */
    private String pch;

    private String bak1;
    //部门id
    private Long depId;

}