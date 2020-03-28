package me.zhengjie.service;

import me.zhengjie.domain.TFjxx;
import me.zhengjie.service.dto.TFjxxDto;
import me.zhengjie.service.dto.TFjxxQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author zengjian
* @date 2020-03-27
*/
public interface TFjxxService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TFjxxQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TFjxxDto>
    */
    List<TFjxxDto> queryAll(TFjxxQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return TFjxxDto
     */
    TFjxxDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return TFjxxDto
    */
    TFjxxDto create(TFjxx resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(TFjxx resources);

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
    void download(List<TFjxxDto> all, HttpServletResponse response) throws IOException;
}