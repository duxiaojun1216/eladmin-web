package me.zhengjie.service.impl;

import me.zhengjie.domain.TFjxx;
import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.service.TFjxxService;
import me.zhengjie.service.dto.TFjxxDto;
import me.zhengjie.utils.*;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

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

    private final TFjxxService fjxxService;



    public TShenbaoxingxiServiceImpl(TShenbaoxingxiRepository tShenbaoxingxiRepository, TShenbaoxingxiMapper tShenbaoxingxiMapper, TFjxxService fjxxService) {
        this.tShenbaoxingxiRepository = tShenbaoxingxiRepository;
        this.tShenbaoxingxiMapper = tShenbaoxingxiMapper;
        this.fjxxService = fjxxService;
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
            map.put(" 部门id",  tShenbaoxingxi.getDepId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public List<TFjxxDto> uploadFj(MultipartFile[] files , String userId ) throws IOException{
        List<TFjxxDto> rest=new ArrayList<>();
        if(files !=null && files.length>0){
            for (MultipartFile file :files) {
                //获取文件名
                String fileName = file.getOriginalFilename();
                //获取后缀名的.下标
                int lastIndex = fileName.lastIndexOf(".");
                //新建文件名
                String newFileName="";
                //文件类型
                String fileType="";
                if(lastIndex!=-1){
                    newFileName=fileName.substring(0,lastIndex);
                    fileType=fileName.substring(lastIndex+1);
                }else {
                    newFileName=fileName;
                }
                String dirPath= this.getClass().getResource("/").getPath().replaceAll("\\/classes", "");
                String tempPath=dirPath+"uploadfile/tempfile/";

                File dirFile= new File(tempPath);
                if(!dirFile.exists()){
                    dirFile.mkdir();
                }
                String fileNameStr = newFileName + getDateStr() + "." + fileType;
                //创建本地文件
                File tempFile =new File(dirPath,fileNameStr);
                //获取存放地址
                String path = tempFile.getPath();
                file.transferTo(tempFile);
                //获取文件大小
                String fileSize = tempFile.length() + "";

                //保存附件信息至数据库
                TFjxx fjxx = new TFjxx();
                //TODO  流程id  申请信息id
                fjxx.setLcid("test");
                fjxx.setSbxxid("test");
                fjxx.setFjmc(fileNameStr);
                fjxx.setCfwz(dirPath);
                fjxx.setFjdx(fileSize);
                fjxx.setUrl(path);
                fjxx.setFjhz(fileType);
                fjxx.setCreateId(userId);
                fjxx.setCreateTime(new Timestamp(System.currentTimeMillis()));
                TFjxxDto tFjxxDto = fjxxService.create(fjxx);
                rest.add(tFjxxDto);
            }
        }
        return rest;
    }
    //时间戳
    public String getDateStr() {
    	Date date = new Date();
        String str = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }


}