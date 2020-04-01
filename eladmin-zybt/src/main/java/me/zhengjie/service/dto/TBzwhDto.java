package me.zhengjie.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author dengjie
* @date 2020-04-01
*/
@Data
public class TBzwhDto implements Serializable {

    /** 标准ID */
    private Integer id;

    /** 资产类型 */
    private String zclx;

    /** 起始时间 */
    private Timestamp qssj;

    /** 终止时间 */
    private Timestamp zzsj;

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

    /** 所属区域 */
    private String ssqy;

    /** 标准 */
    private String bz;
}