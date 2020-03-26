package me.zhengjie.service.impl;

import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.repository.TShenbaoxingxiRepository;
import me.zhengjie.service.TShenbaoxingxiService;
import me.zhengjie.service.dto.TShenbaoxingxiDto;
import me.zhengjie.service.dto.TShenbaoxingxiQueryCriteria;
import me.zhengjie.service.mapper.TShenbaoxingxiMapper;
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
* @date 2020-03-25
*/
@Service
//@CacheConfig(cacheNames = "tShenbaoxingxi")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TShenbaoxingxiServiceImpl implements TShenbaoxingxiService {

    private final TShenbaoxingxiRepository tShenbaoxingxiRepository;

    private final TShenbaoxingxiMapper tShenbaoxingxiMapper;

    public TShenbaoxingxiServiceImpl(TShenbaoxingxiRepository tShenbaoxingxiRepository, TShenbaoxingxiMapper tShenbaoxingxiMapper) {
        this.tShenbaoxingxiRepository = tShenbaoxingxiRepository;
        this.tShenbaoxingxiMapper = tShenbaoxingxiMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(TShenbaoxingxiQueryCriteria criteria, Pageable pageable){
        Page<TShenbaoxingxi> page = tShenbaoxingxiRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(tShenbaoxingxiMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<TShenbaoxingxiDto> queryAll(TShenbaoxingxiQueryCriteria criteria){
        return tShenbaoxingxiMapper.toDto(tShenbaoxingxiRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public TShenbaoxingxiDto findById(Long id) {
        TShenbaoxingxi tShenbaoxingxi = tShenbaoxingxiRepository.findById(id).orElseGet(TShenbaoxingxi::new);
        ValidationUtil.isNull(tShenbaoxingxi.getId(),"TShenbaoxingxi","id",id);
        return tShenbaoxingxiMapper.toDto(tShenbaoxingxi);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public TShenbaoxingxiDto create(TShenbaoxingxi resources) {
        return tShenbaoxingxiMapper.toDto(tShenbaoxingxiRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(TShenbaoxingxi resources) {
        TShenbaoxingxi tShenbaoxingxi = tShenbaoxingxiRepository.findById(resources.getId()).orElseGet(TShenbaoxingxi::new);
        ValidationUtil.isNull( tShenbaoxingxi.getId(),"TShenbaoxingxi","id",resources.getId());
        tShenbaoxingxi.copy(resources);
        tShenbaoxingxiRepository.save(tShenbaoxingxi);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            tShenbaoxingxiRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TShenbaoxingxiDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TShenbaoxingxiDto tShenbaoxingxi : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("申报人id", tShenbaoxingxi.getSbrid());
            map.put("房产id", tShenbaoxingxi.getFcid());
            map.put("合同时间", tShenbaoxingxi.getHtsj());
            map.put("网签时间", tShenbaoxingxi.getWqsj());
            map.put("房产金额", tShenbaoxingxi.getFcje());
            map.put("房产类型", tShenbaoxingxi.getFclx());
            map.put("补贴金额", tShenbaoxingxi.getBtje());
            map.put("补贴余额", tShenbaoxingxi.getBtye());
            map.put("申请人类型", tShenbaoxingxi.getSqrlx());
            map.put("备用", tShenbaoxingxi.getBak2());
            map.put("备用", tShenbaoxingxi.getBak3());
            map.put("备用", tShenbaoxingxi.getBak4());
            map.put("备用", tShenbaoxingxi.getBak5());
            map.put("创建人", tShenbaoxingxi.getCreateId());
            map.put("创建时间", tShenbaoxingxi.getCreateTime());
            map.put("修改人", tShenbaoxingxi.getUpdateId());
            map.put("修改时间", tShenbaoxingxi.getUpdateTime());
            map.put("住建复核状态", tShenbaoxingxi.getZjfh());
            map.put(" bak1",  tShenbaoxingxi.getBak1());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}