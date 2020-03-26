package me.zhengjie.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.service.dto.TShenbaoxingxiDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author zengjian
* @date 2020-03-25
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TShenbaoxingxiMapper extends BaseMapper<TShenbaoxingxiDto, TShenbaoxingxi> {

}