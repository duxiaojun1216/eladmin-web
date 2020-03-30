package test.service;

import test.domain.Dict;
import test.service.dto.DictDto;
import test.service.dto.DictQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author wangwei
* @date 2020-03-28
*/
public interface DictService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(DictQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<DictDto>
    */
    List<DictDto> queryAll(DictQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return DictDto
     */
    DictDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return DictDto
    */
    DictDto create(Dict resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Dict resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<DictDto> all, HttpServletResponse response) throws IOException;
}