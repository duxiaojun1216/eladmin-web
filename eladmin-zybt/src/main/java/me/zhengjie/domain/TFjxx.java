package me.zhengjie.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @author zengjian
* @date 2020-03-27
*/
@Entity
@Data
@Table(name="t_fjxx")
public class TFjxx implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 附件名称 */
    @Column(name = "fjmc")
    private String fjmc;

    /** 附件后缀 */
    @Column(name = "fjhz")
    private String fjhz;

    /** 附件大小 */
    @Column(name = "fjdx")
    private String fjdx;

    /** 流程id */
    @Column(name = "lcid",nullable = false)
    @NotBlank
    private String lcid;

    /** 存放位置 */
    @Column(name = "cfwz")
    private String cfwz;

    /** 访问地址 */
    @Column(name = "url")
    private String url;

    /** 申报信息id */
    @Column(name = "sbxxid",nullable = false)
    @NotBlank
    private String sbxxid;

    /** 备用字段 */
    @Column(name = "bak1")
    private String bak1;

    /** 备用字段 */
    @Column(name = "bak2")
    private String bak2;

    /** 备用字段 */
    @Column(name = "bak3")
    private String bak3;

    public void copy(TFjxx source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}