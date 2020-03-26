package me.zhengjie.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.domain.TBzwh;
import me.zhengjie.service.dto.TBzwhDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author dengjie
* @date 2020-03-26
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TBzwhMapper extends BaseMapper<TBzwhDto, TBzwh> {

}