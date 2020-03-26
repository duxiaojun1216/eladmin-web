package me.zhengjie.service;

import me.zhengjie.domain.TBzwh;
import me.zhengjie.service.dto.TBzwhDto;
import me.zhengjie.service.dto.TBzwhQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author dengjie
* @date 2020-03-26
*/
public interface TBzwhService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TBzwhQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TBzwhDto>
    */
    List<TBzwhDto> queryAll(TBzwhQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return TBzwhDto
     */
    TBzwhDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return TBzwhDto
    */
    TBzwhDto create(TBzwh resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(TBzwh resources);

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
    void download(List<TBzwhDto> all, HttpServletResponse response) throws IOException;
}