package me.zhengjie.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.domain.TEntrust;
import me.zhengjie.service.dto.TEntrustDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author zengjian
* @date 2020-04-01
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TEntrustMapper extends BaseMapper<TEntrustDto, TEntrust> {

}