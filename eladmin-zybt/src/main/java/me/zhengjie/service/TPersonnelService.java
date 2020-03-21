package me.zhengjie.service;

import me.zhengjie.domain.TPersonnel;
import me.zhengjie.service.dto.TPersonnelDto;
import me.zhengjie.service.dto.TPersonnelQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author zengjina
* @date 2020-03-21
*/
public interface TPersonnelService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TPersonnelQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TPersonnelDto>
    */
    List<TPersonnelDto> queryAll(TPersonnelQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return TPersonnelDto
     */
    TPersonnelDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return TPersonnelDto
    */
    TPersonnelDto create(TPersonnel resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(TPersonnel resources);

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
    void download(List<TPersonnelDto> all, HttpServletResponse response) throws IOException;
}