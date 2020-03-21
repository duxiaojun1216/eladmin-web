package me.zhengjie.service.impl;

import me.zhengjie.domain.TPersonnel;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.repository.TPersonnelRepository;
import me.zhengjie.service.TPersonnelService;
import me.zhengjie.service.dto.TPersonnelDto;
import me.zhengjie.service.dto.TPersonnelQueryCriteria;
import me.zhengjie.service.mapper.TPersonnelMapper;
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
* @author zengjina
* @date 2020-03-21
*/
@Service
//@CacheConfig(cacheNames = "tPersonnel")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TPersonnelServiceImpl implements TPersonnelService {

    private final TPersonnelRepository tPersonnelRepository;

    private final TPersonnelMapper tPersonnelMapper;

    public TPersonnelServiceImpl(TPersonnelRepository tPersonnelRepository, TPersonnelMapper tPersonnelMapper) {
        this.tPersonnelRepository = tPersonnelRepository;
        this.tPersonnelMapper = tPersonnelMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(TPersonnelQueryCriteria criteria, Pageable pageable){
        Page<TPersonnel> page = tPersonnelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tPersonnelMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<TPersonnelDto> queryAll(TPersonnelQueryCriteria criteria){
        return tPersonnelMapper.toDto(tPersonnelRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public TPersonnelDto findById(Long id) {
        TPersonnel tPersonnel = tPersonnelRepository.findById(id).orElseGet(TPersonnel::new);
        ValidationUtil.isNull(tPersonnel.getId(),"TPersonnel","id",id);
        return tPersonnelMapper.toDto(tPersonnel);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public TPersonnelDto create(TPersonnel resources) {
        return tPersonnelMapper.toDto(tPersonnelRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(TPersonnel resources) {
        TPersonnel tPersonnel = tPersonnelRepository.findById(resources.getId()).orElseGet(TPersonnel::new);
        ValidationUtil.isNull( tPersonnel.getId(),"TPersonnel","id",resources.getId());
        tPersonnel.copy(resources);
        tPersonnelRepository.save(tPersonnel);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            tPersonnelRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TPersonnelDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TPersonnelDto tPersonnel : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("身份证号", tPersonnel.getCardId());
            map.put("电话", tPersonnel.getPhone());
            map.put("身份类型", tPersonnel.getType());
            map.put("创建时间", tPersonnel.getCreateTime());
            map.put("创建人Id", tPersonnel.getCreateid());
            map.put("修改时间", tPersonnel.getUpdateTime());
            map.put("修改人ID", tPersonnel.getUpdateId());
            map.put("备注", tPersonnel.getMark());
            map.put("状态（是否启用）", tPersonnel.getState());
            map.put("备用字段2", tPersonnel.getBak2());
            map.put("备用字段3", tPersonnel.getBak3());
            map.put("备用字段4", tPersonnel.getBak4());
            map.put("备用字段5", tPersonnel.getBak5());
            map.put("姓名", tPersonnel.getTname());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}