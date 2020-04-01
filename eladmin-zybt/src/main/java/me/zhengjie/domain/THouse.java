package me.zhengjie.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author zengjian
* @date 2020-03-27
*/
@Entity
@Data
@Table(name="t_house")
public class THouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 备用 */
    @Column(name = "bak1")
    private String bak1;

    /** 备用 */
    @Column(name = "bak2")
    private String bak2;

    /** 备用 */
    @Column(name = "bak3")
    private String bak3;

    /** 备用 */
    @Column(name = "bak4")
    private String bak4;

    /** 备用 */
    @Column(name = "bak5")
    private String bak5;

    /** 建筑类型 */
    @Column(name = "jzlx")
    private String jzlx;

    /** 产权年限 */
    @Column(name = "cqnx")
    private String cqnx;

    /** 开发商 */
    @Column(name = "kfs")
    private String kfs;

    /** 面积 */
    @Column(name = "fcmj")
    private String fcmj;

    /** 建成时间 */
    @Column(name = "jcsj")
    private Timestamp jcsj;

    /** 所处位置 */
    @Column(name = "scwz")
    private String scwz;

    /** 交易类型 */
    @Column(name = "jylx")
    private String jylx;

    /** 资产备注 */
    @Column(name = "zcbz")
    private String zcbz;

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

    //资产备注
    @Column(name = "fwlx")
    private String fwlx;

    public void copy(THouse source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}