package me.zhengjie.service.impl;

import me.zhengjie.domain.TEntrust;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.repository.TEntrustRepository;
import me.zhengjie.service.TEntrustService;
import me.zhengjie.service.dto.TEntrustDto;
import me.zhengjie.service.dto.TEntrustQueryCriteria;
import me.zhengjie.service.mapper.TEntrustMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
* @date 2020-04-01
*/
@Service
//@CacheConfig(cacheNames = "tEntrust")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TEntrustServiceImpl implements TEntrustService {

    private final TEntrustRepository tEntrustRepository;

    private final TEntrustMapper tEntrustMapper;

    public TEntrustServiceImpl(TEntrustRepository tEntrustRepository, TEntrustMapper tEntrustMapper) {
        this.tEntrustRepository = tEntrustRepository;
        this.tEntrustMapper = tEntrustMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(TEntrustQueryCriteria criteria, Pageable pageable){
        Page<TEntrust> page = tEntrustRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tEntrustMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<TEntrustDto> queryAll(TEntrustQueryCriteria criteria){
        return tEntrustMapper.toDto(tEntrustRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public TEntrustDto findById(Long id) {
        TEntrust tEntrust = tEntrustRepository.findById(id).orElseGet(TEntrust::new);
        ValidationUtil.isNull(tEntrust.getId(),"TEntrust","id",id);
        return tEntrustMapper.toDto(tEntrust);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public TEntrustDto create(TEntrust resources) {
        return tEntrustMapper.toDto(tEntrustRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(TEntrust resources) {
        TEntrust tEntrust = tEntrustRepository.findById(resources.getId()).orElseGet(TEntrust::new);
        ValidationUtil.isNull( tEntrust.getId(),"TEntrust","id",resources.getId());
        tEntrust.copy(resources);
        tEntrustRepository.save(tEntrust);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            tEntrustRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TEntrustDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TEntrustDto tEntrust : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("代办企业名称", tEntrust.getEnterprisename());
            map.put("代办人姓名", tEntrust.getPersonname());
            map.put("代办人电话号码", tEntrust.getTelephone());
            map.put("代办人身份证号码", tEntrust.getCardid());
            map.put("创建时间", tEntrust.getCreateTime());
            map.put("创建人", tEntrust.getCreateId());
            map.put("修改时间", tEntrust.getUpdateTime());
            map.put("修改人", tEntrust.getUpdateId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}