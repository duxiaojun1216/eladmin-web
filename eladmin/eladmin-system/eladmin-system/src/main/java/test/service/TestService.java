package test.service;

import test.domain.Test;
import test.service.dto.TestDto;
import test.service.dto.TestQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author wangwei
* @date 2020-02-29
*/
public interface TestService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TestQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TestDto>
    */
    List<TestDto> queryAll(TestQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return TestDto
     */
    TestDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return TestDto
    */
    TestDto create(Test resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Test resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Integer[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<TestDto> all, HttpServletResponse response) throws IOException;
}