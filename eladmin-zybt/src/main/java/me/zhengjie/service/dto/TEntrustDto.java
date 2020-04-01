package me.zhengjie.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author zengjian
* @date 2020-04-01
*/
@Data
public class TEntrustDto implements Serializable {

    /** 主键ID */
    private Long id;

    /** 代办企业名称 */
    private String enterprisename;

    /** 代办人姓名 */
    private String personname;

    /** 代办人电话号码 */
    private String telephone;

    /** 代办人身份证号码 */
    private String cardid;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createId;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateId;
}