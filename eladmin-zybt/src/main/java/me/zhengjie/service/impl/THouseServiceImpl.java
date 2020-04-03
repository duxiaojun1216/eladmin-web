package me.zhengjie.service.impl;

import me.zhengjie.domain.THouse;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.repository.THouseRepository;
import me.zhengjie.service.THouseService;
import me.zhengjie.service.dto.THouseDto;
import me.zhengjie.service.dto.THouseQueryCriteria;
import me.zhengjie.service.mapper.THouseMapper;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
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

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.persistence.Query;

/**
* @author zengjian
* @date 2020-03-27
*/
@Service
//@CacheConfig(cacheNames = "tHouse")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class THouseServiceImpl implements THouseService {

    private final THouseRepository tHouseRepository;

    private final THouseMapper tHouseMapper;

    public THouseServiceImpl(THouseRepository tHouseRepository, THouseMapper tHouseMapper) {
        this.tHouseRepository = tHouseRepository;
        this.tHouseMapper = tHouseMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(THouseQueryCriteria criteria, Pageable pageable){
        Page<THouse> page = tHouseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tHouseMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<THouseDto> queryAll(THouseQueryCriteria criteria){
        return tHouseMapper.toDto(tHouseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public THouseDto findById(Long id) {
        THouse tHouse = tHouseRepository.findById(id).orElseGet(THouse::new);
        ValidationUtil.isNull(tHouse.getId(),"THouse","id",id);
        return tHouseMapper.toDto(tHouse);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public THouseDto create(THouse resources) {
        return tHouseMapper.toDto(tHouseRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(THouse resources) {
        THouse tHouse = tHouseRepository.findById(resources.getId()).orElseGet(THouse::new);
        ValidationUtil.isNull( tHouse.getId(),"THouse","id",resources.getId());
        tHouse.copy(resources);
        tHouseRepository.save(tHouse);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {   	  	
        for (Long id : ids) {
            tHouseRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<THouseDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (THouseDto tHouse : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("备用", tHouse.getBak1());
            map.put("备用", tHouse.getBak2());
            map.put("备用", tHouse.getBak3());
            map.put("备用", tHouse.getBak4());
            map.put("备用", tHouse.getBak5());
            map.put("建筑类型", tHouse.getJzlx());
            map.put("产权年限", tHouse.getCqnx());
            map.put("开发商", tHouse.getKfs());
            map.put("面积", tHouse.getFcmj());
            map.put("建成时间", tHouse.getJcsj());
            map.put("所处位置", tHouse.getScwz());
            map.put("创建人", tHouse.getCreateId());
            map.put("创建时间", tHouse.getCreateTime());
            map.put("修改人", tHouse.getUpdateId());
            map.put("修改时间", tHouse.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}