package test.service.mapper;

import me.zhengjie.base.BaseMapper;
import test.domain.Test;
import test.service.dto.TestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author wangwei
* @date 2020-02-29
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestMapper extends BaseMapper<TestDto, Test> {

}