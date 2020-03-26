package me.zhengjie.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author dengjie
* @date 2020-03-26
*/
@Data
public class TBzwhQueryCriteria{

    /** 精确 */
    @Query
    private String zclx;
}