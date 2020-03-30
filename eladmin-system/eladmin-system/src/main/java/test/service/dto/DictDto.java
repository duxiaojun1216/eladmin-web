package test.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author wangwei
* @date 2020-03-28
*/
@Data
public class DictDto implements Serializable {

    private Long id;

    /** 字典名称 */
    private String name;

    /** 描述 */
    private String remark;

    /** 创建日期 */
    private Timestamp createTime;

    /** 类型 */
    private String type;
}