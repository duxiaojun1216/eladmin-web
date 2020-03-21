package me.zhengjie.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author zengjina
* @date 2020-03-21
*/
@Entity
@Data
@Table(name="t_personnel")
public class TPersonnel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    /** 电话 */
    @Column(name = "phone",nullable = false)
    @NotBlank
    private String phone;

    /** 身份类型 */
    @Column(name = "type",nullable = false)
    @NotBlank
    private String type;


    /** 创建人Id */
    @Column(name = "createID",nullable = false)
    private String createid;

    /** 备注 */
    @Column(name = "mark")
    private String mark;

    /** 状态（是否启用） */
    @Column(name = "state")
    private String state;

    /** 备用字段2 */
    @Column(name = "bak2")
    private String bak2;

    /** 备用字段3 */
    @Column(name = "bak3")
    private String bak3;

    /** 备用字段4 */
    @Column(name = "bak4")
    private String bak4;

    /** 备用字段5 */
    @Column(name = "bak5")
    private String bak5;

    /** 姓名 */
    @Column(name = "Tname",nullable = false)
    @NotBlank
    private String tname;

    /** 身份证号 */
    @Column(name = "card_id",nullable = false)
    @NotBlank
    private String cardId;
    /** 创建时间 */
    @Column(name = "create_time",nullable = false)
    private Timestamp createTime;

    @Column(name = "update_id")
    private String updateId;

    @Column(name = "update_time",nullable = false)
    private Timestamp updateTime;

    public void copy(TPersonnel source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}