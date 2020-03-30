package test.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author wangwei
* @date 2020-03-28
*/
@Entity
@Data
@Table(name="dict")
public class Dict implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 字典名称 */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    /** 描述 */
    @Column(name = "remark")
    private String remark;

    /** 创建日期 */
    @Column(name = "create_time")
    private Timestamp createTime;

    /** 类型 */
    @Column(name = "type",nullable = false)
    @NotBlank
    private String type;

    public void copy(Dict source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}