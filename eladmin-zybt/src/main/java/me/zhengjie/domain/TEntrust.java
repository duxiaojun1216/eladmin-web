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
* @date 2020-04-01
*/
@Entity
@Data
@Table(name="t_entrust")
public class TEntrust implements Serializable {

    /** 主键ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 代办企业名称 */
    @Column(name = "enterpriseName")
    private String enterprisename;

    /** 代办人姓名 */
    @Column(name = "personName")
    private String personname;

    /** 代办人电话号码 */
    @Column(name = "telephone")
    private String telephone;

    /** 代办人身份证号码 */
    @Column(name = "cardID")
    private String cardid;

    /** 创建时间 */
    @Column(name = "create_time")
    private Timestamp createTime;

    /** 创建人 */
    @Column(name = "create_id")
    private String createId;

    /** 修改时间 */
    @Column(name = "update_time")
    private Timestamp updateTime;

    /** 修改人 */
    @Column(name = "update_id")
    private String updateId;

    public void copy(TEntrust source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}