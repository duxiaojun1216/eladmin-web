package me.zhengjie.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

/**
* @author dengjie
* @date 2020-04-01
*/
@Data
public class TBzwhQueryCriteria{

    /** 精确 */
    @Query
    private String zclx;
}
