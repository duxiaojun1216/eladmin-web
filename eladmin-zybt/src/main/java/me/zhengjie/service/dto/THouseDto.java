package me.zhengjie.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author zengjian
* @date 2020-03-27
*/
@Data
public class THouseDto implements Serializable {

    private Long id;

    /** 备用 */
    private String bak1;

    /** 备用 */
    private String bak2;

    /** 备用 */
    private String bak3;

    /** 备用 */
    private String bak4;

    /** 备用 */
    private String bak5;

    /** 建筑类型 */
    private String jzlx;

    /** 产权年限 */
    private String cqnx;

    /** 开发商 */
    private String kfs;

    /** 面积 */
    private String fcmj;

    /** 建成时间 */
    private Timestamp jcsj;

    /** 所处位置 */
    private String scwz;

    /** 创建人 */
    private String createId;

    /** 创建时间 */
    private Timestamp createTime;

    /** 修改人 */
    private String updateId;

    /** 修改时间 */
    private Timestamp updateTime;
}