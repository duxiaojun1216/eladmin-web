package me.zhengjie.service;

import me.zhengjie.common.vo.Result;
import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.service.dto.ShxxHz;
import me.zhengjie.service.dto.TFjxxDto;
import me.zhengjie.service.dto.TShenbaoxingxiDto;
import me.zhengjie.service.dto.TShenbaoxingxiQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author zengjian
* @date 2020-03-25
*/
public interface TShenbaoxingxiService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TShenbaoxingxiQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TShenbaoxingxiDto>
    */
    List<TShenbaoxingxiDto> queryAll(TShenbaoxingxiQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return TShenbaoxingxiDto
     */
    TShenbaoxingxiDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return TShenbaoxingxiDto
    */
    TShenbaoxingxiDto create(TShenbaoxingxi resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(TShenbaoxingxi resources);

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

    void download(List<TShenbaoxingxiDto> all, HttpServletResponse response) throws IOException;
    /**
     * sha
     * @param file 上传的文件
     */
    List<TFjxxDto> uploadFj(MultipartFile[] file,String userId ) throws IOException;

    /**
     * 业务受理 表单保存
     * @param shxxHz 表单信息
     * @return
     */
    Result<Object> addShenBaoXinXi(ShxxHz shxxHz);

    /**
     * 根据 id 获取业务受理 表单信息
     * @param id
     * @return
     */
    Result<ShxxHz> getYeWuShouLiData(Long id);

    /**
     * 获取申报统计数据
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    List<Map<String,String>> getBusinessTotalData(String startTime, String endTime, String type);
    //List<BusinessTotal> getBusinessTotalData(String startTime, String endTime, String type);
}