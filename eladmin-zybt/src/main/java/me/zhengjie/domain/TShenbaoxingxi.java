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
* @date 2020-03-25
*/
@Entity
@Data
@Table(name="t_shenbaoxingxi")
public class TShenbaoxingxi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 申报人id */
    @Column(name = "sbrid")
    private Long sbrid;

    /** 房产id */
    @Column(name = "fcid")
    private Long fcid;

    /** 合同时间 */
    @Column(name = "htsj",nullable = false)
    @NotNull
    private Timestamp htsj;

    /** 网签时间 */
    @Column(name = "wqsj",nullable = false)
    @NotNull
    private Timestamp wqsj;

    /** 房产金额 */
    @Column(name = "fcje")
    private String fcje;

    /** 房产类型 */
    @Column(name = "fclx")
    private String fclx;

    /** 补贴金额 */
    @Column(name = "btje")
    private String btje;

    /** 补贴余额 */
    @Column(name = "btye")
    private String btye;

    /** 申请人类型 */
    @Column(name = "sqrlx")
    private String sqrlx;

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

    /** 住建复核状态 */
    @Column(name = "zjfh")
    private String zjfh;

    @Column(name = "bak1")
    private String bak1;

    //部门id
    @Column(name = "dep_id")
    private Long  depId;

    //部门id
    @Column(name = "pch")
    private String  pch;

    public void copy(TShenbaoxingxi source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}