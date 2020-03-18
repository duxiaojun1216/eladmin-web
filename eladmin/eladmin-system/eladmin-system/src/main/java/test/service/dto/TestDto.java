package test.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author wangwei
* @date 2020-02-29
*/
@Data
public class TestDto implements Serializable {

    private Integer id;

    private String name;

    private String remark;
}