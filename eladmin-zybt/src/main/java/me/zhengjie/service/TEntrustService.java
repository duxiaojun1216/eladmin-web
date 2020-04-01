package me.zhengjie.service;

import me.zhengjie.domain.TEntrust;
import me.zhengjie.service.dto.TEntrustDto;
import me.zhengjie.service.dto.TEntrustQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author zengjian
* @date 2020-04-01
*/
public interface TEntrustService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TEntrustQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TEntrustDto>
    */
    List<TEntrustDto> queryAll(TEntrustQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return TEntrustDto
     */
    TEntrustDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return TEntrustDto
    */
    TEntrustDto create(TEntrust resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(TEntrust resources);

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
    void download(List<TEntrustDto> all, HttpServletResponse response) throws IOException;
}