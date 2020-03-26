package me.zhengjie.service.impl;

import me.zhengjie.domain.TBzwh;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.repository.TBzwhRepository;
import me.zhengjie.service.TBzwhService;
import me.zhengjie.service.dto.TBzwhDto;
import me.zhengjie.service.dto.TBzwhQueryCriteria;
import me.zhengjie.service.mapper.TBzwhMapper;
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
* @author dengjie
* @date 2020-03-26
*/
@Service
//@CacheConfig(cacheNames = "tBzwh")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TBzwhServiceImpl implements TBzwhService {

    private final TBzwhRepository tBzwhRepository;

    private final TBzwhMapper tBzwhMapper;

    public TBzwhServiceImpl(TBzwhRepository tBzwhRepository, TBzwhMapper tBzwhMapper) {
        this.tBzwhRepository = tBzwhRepository;
        this.tBzwhMapper = tBzwhMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(TBzwhQueryCriteria criteria, Pageable pageable){
        Page<TBzwh> page = tBzwhRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tBzwhMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<TBzwhDto> queryAll(TBzwhQueryCriteria criteria){
        return tBzwhMapper.toDto(tBzwhRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public TBzwhDto findById(Integer id) {
        TBzwh tBzwh = tBzwhRepository.findById(id).orElseGet(TBzwh::new);
        ValidationUtil.isNull(tBzwh.getId(),"TBzwh","id",id);
        return tBzwhMapper.toDto(tBzwh);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public TBzwhDto create(TBzwh resources) {
        return tBzwhMapper.toDto(tBzwhRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(TBzwh resources) {
        TBzwh tBzwh = tBzwhRepository.findById(resources.getId()).orElseGet(TBzwh::new);
        ValidationUtil.isNull( tBzwh.getId(),"TBzwh","id",resources.getId());
        tBzwh.copy(resources);
        tBzwhRepository.save(tBzwh);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            tBzwhRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TBzwhDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TBzwhDto tBzwh : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("资产类型", tBzwh.getZclx());
            map.put("起始时间", tBzwh.getQssj());
            map.put("终止时间", tBzwh.getZzsj());
            map.put("比例", tBzwh.getBl());
            map.put("备用", tBzwh.getBak3());
            map.put("备用", tBzwh.getBak4());
            map.put("备用", tBzwh.getBak5());
            map.put("创建人", tBzwh.getCreateId());
            map.put("创建时间", tBzwh.getCreateTime());
            map.put("修改人", tBzwh.getUpdateId());
            map.put("修改时间", tBzwh.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}