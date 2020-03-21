package me.zhengjie.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.domain.TPersonnel;
import me.zhengjie.service.dto.TPersonnelDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author zengjina
* @date 2020-03-21
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TPersonnelMapper extends BaseMapper<TPersonnelDto, TPersonnel> {

}