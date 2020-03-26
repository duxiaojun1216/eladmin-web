package me.zhengjie.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author dengjie
* @date 2020-03-26
*/
@Entity
@Data
@Table(name="t_bzwh")
public class TBzwh implements Serializable {

    /** 标准ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 资产类型 */
    @Column(name = "zclx")
    private String zclx;

    /** 起始时间 */
    @Column(name = "qssj")
    private Timestamp qssj;

    /** 终止时间 */
    @Column(name = "zzsj")
    private Timestamp zzsj;

    /** 比例 */
    @Column(name = "bl")
    private String bl;

    /** 备用 */
    @Column(name = "bak3")
    private String bak3;

    /** 备用 */
    @Column(name = "bak4")
    private String bak4;

    /** 备用 */
    @Column(name = "bak5")
    private String bak5;

    /** 创建人 */
    @Column(name = "create_id")
    private String createId;

    /** 创建时间 */
    @Column(name = "create_time")
    private Timestamp createTime;

    /** 修改人 */
    @Column(name = "update_id")
    private String updateId;

    /** 修改时间 */
    @Column(name = "update_time")
    private Timestamp updateTime;

    public void copy(TBzwh source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}