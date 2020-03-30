package test.service.impl;

import test.domain.Dict;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import test.repository.DictRepository;
import test.service.DictService;
import test.service.dto.DictDto;
import test.service.dto.DictQueryCriteria;
import test.service.mapper.DictMapper;
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
* @author wangwei
* @date 2020-03-28
*/
@Service
//@CacheConfig(cacheNames = "dict")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl implements DictService {

    private final DictRepository dictRepository;

    private final DictMapper dictMapper;

    public DictServiceImpl(DictRepository dictRepository, DictMapper dictMapper) {
        this.dictRepository = dictRepository;
        this.dictMapper = dictMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(DictQueryCriteria criteria, Pageable pageable){
        Page<Dict> page = dictRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(dictMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<DictDto> queryAll(DictQueryCriteria criteria){
        return dictMapper.toDto(dictRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public DictDto findById(Long id) {
        Dict dict = dictRepository.findById(id).orElseGet(Dict::new);
        ValidationUtil.isNull(dict.getId(),"Dict","id",id);
        return dictMapper.toDto(dict);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public DictDto create(Dict resources) {
        return dictMapper.toDto(dictRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Dict resources) {
        Dict dict = dictRepository.findById(resources.getId()).orElseGet(Dict::new);
        ValidationUtil.isNull( dict.getId(),"Dict","id",resources.getId());
        dict.copy(resources);
        dictRepository.save(dict);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            dictRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<DictDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DictDto dict : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("字典名称", dict.getName());
            map.put("描述", dict.getRemark());
            map.put("创建日期", dict.getCreateTime());
            map.put("类型", dict.getType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}