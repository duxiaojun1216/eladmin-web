package test.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author wangwei
* @date 2020-03-28
*/
@Data
public class DictQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String type;
}