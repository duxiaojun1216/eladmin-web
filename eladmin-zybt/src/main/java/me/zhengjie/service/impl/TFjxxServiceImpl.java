package me.zhengjie.service.impl;

import me.zhengjie.domain.TFjxx;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.repository.TFjxxRepository;
import me.zhengjie.service.TFjxxService;
import me.zhengjie.service.dto.TFjxxDto;
import me.zhengjie.service.dto.TFjxxQueryCriteria;
import me.zhengjie.service.mapper.TFjxxMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author zengjian
* @date 2020-03-27
*/
@Service
//@CacheConfig(cacheNames = "tFjxx")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TFjxxServiceImpl implements TFjxxService {

    private final TFjxxRepository tFjxxRepository;

    private final TFjxxMapper tFjxxMapper;

    public TFjxxServiceImpl(TFjxxRepository tFjxxRepository, TFjxxMapper tFjxxMapper) {
        this.tFjxxRepository = tFjxxRepository;
        this.tFjxxMapper = tFjxxMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(TFjxxQueryCriteria criteria, Pageable pageable){
        Page<TFjxx> page = tFjxxRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tFjxxMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<TFjxxDto> queryAll(TFjxxQueryCriteria criteria){
        return tFjxxMapper.toDto(tFjxxRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public TFjxxDto findById(Long id) {
        TFjxx tFjxx = tFjxxRepository.findById(id).orElseGet(TFjxx::new);
        ValidationUtil.isNull(tFjxx.getId(),"TFjxx","id",id);
        return tFjxxMapper.toDto(tFjxx);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public TFjxxDto create(TFjxx resources) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        resources.setId(snowflake.nextId()); 
        return tFjxxMapper.toDto(tFjxxRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(TFjxx resources) {
        TFjxx tFjxx = tFjxxRepository.findById(resources.getId()).orElseGet(TFjxx::new);
        ValidationUtil.isNull( tFjxx.getId(),"TFjxx","id",resources.getId());
        tFjxx.copy(resources);
        tFjxxRepository.save(tFjxx);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            tFjxxRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TFjxxDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TFjxxDto tFjxx : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("附件名称", tFjxx.getFjmc());
            map.put("附件后缀", tFjxx.getFjhz());
            map.put("附件大小", tFjxx.getFjdx());
            map.put("流程id", tFjxx.getLcid());
            map.put("存放位置", tFjxx.getCfwz());
            map.put("访问地址", tFjxx.getUrl());
            map.put("申报信息id", tFjxx.getSbxxid());
            map.put("备用字段", tFjxx.getBak1());
            map.put("备用字段", tFjxx.getBak2());
            map.put("备用字段", tFjxx.getBak3());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}