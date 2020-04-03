package me.zhengjie.service.impl;

import me.zhengjie.common.utils.SecurityUtil;
import me.zhengjie.domain.*;
import me.zhengjie.entity.ActBusiness;
import me.zhengjie.repository.TShenbaoxingxiRepository;
import me.zhengjie.service.*;
import me.zhengjie.service.business.LeaveService;
import me.zhengjie.service.dto.*;
import me.zhengjie.service.mapper.TShenbaoxingxiMapper;
import me.zhengjie.system.service.DeptService;
import me.zhengjie.system.service.dto.UserDto;
import me.zhengjie.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

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
    @Autowired
    private TPersonnelService tPersonnelService;
    @Autowired
    private THouseService tHouseService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private ActBusinessService actBusinessService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private TEntrustService entrustService;

    @Autowired
    private final DeptService deptService;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    public TShenbaoxingxiServiceImpl(TShenbaoxingxiRepository tShenbaoxingxiRepository, TShenbaoxingxiMapper tShenbaoxingxiMapper, TFjxxService fjxxService, DeptService deptService) {
        this.tShenbaoxingxiRepository = tShenbaoxingxiRepository;
        this.tShenbaoxingxiMapper = tShenbaoxingxiMapper;
        this.fjxxService = fjxxService;
        this.deptService = deptService;
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
    public List<TFjxxDto> uploadFj(MultipartFile[] files , EnclosureParameter fileData ) throws IOException{
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
                fjxx.setCreateId(fileData.getIdStr());
                fjxx.setCreateTime(new Timestamp(System.currentTimeMillis()));
                fjxx.setFileType(fileData.getType());
                fjxx.setFileTypeName(fileData.getName());
                TFjxxDto tFjxxDto = fjxxService.create(fjxx);
                rest.add(tFjxxDto);
            }
        }
        return rest;
    }

    @Override
    public TShenbaoxingxiDto addShenBaoXinXi(ShxxHz shxxHz) {
        //房产信息
        THouse tHouse = new THouse();
        //申请人信息
        TPersonnel tPersonnel = new TPersonnel();
        //审核报表信息
        TShenbaoxingxi tShenbaoxingxi = new TShenbaoxingxi();
        //委托信息
        TEntrust entrust=null;
        // 获取登录人信息 设置创建人 时间 区域
        UserDto currUser = securityUtil.getCurrUser();
        String userId = currUser.getId().toString();
        Long region = deptService.findById(currUser.getDept().getId()).getPid();
       // Long region =7l;

        tPersonnel.setCreateid(userId);
        tPersonnel.setCreateTime(new Timestamp(System.currentTimeMillis()));

        tHouse.setCreateId(userId);
        tHouse.setCreateTime(new Timestamp(System.currentTimeMillis()));


        tShenbaoxingxi.setSsqy(region.toString());
        tShenbaoxingxi.setCreateId(userId);
        tShenbaoxingxi.setCreateTime(new Timestamp(System.currentTimeMillis()));

        //委托信息
        if("1".equals(shxxHz.getSfwt())){
            entrust= new TEntrust();
            entrust.setCardid(userId);
            entrust.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }

        copy(tHouse,tPersonnel,tShenbaoxingxi,entrust,shxxHz);
//        String str=new SimpleDateFormat("yyyy-MM-dd").format(shxxHz.getWqrq());
        TShenbaoxingxiDto tShenbaoxingxiDto=null;
        try {
            //申报人员 信息
            TPersonnelDto tPersonnelDto = tPersonnelService.create(tPersonnel);
            tShenbaoxingxi.setSbrid(tPersonnelDto.getId());

            //申报房产信息
            THouseDto tHouseDto = tHouseService.create(tHouse);
            tShenbaoxingxi.setFcid(tHouseDto.getId());

            //获取编号
            String bh= getCode(tShenbaoxingxi.getSqrlx(), tShenbaoxingxi.getFclx());

            TEntrustDto tEntrustDto = entrustService.create(entrust);
            tShenbaoxingxi.setWtxxId(tEntrustDto.getId());

            //设置编号 是否委托
            tShenbaoxingxi.setBh(bh);
            tShenbaoxingxi.setSfwt(shxxHz.getSfwt());
            tShenbaoxingxiDto = create(tShenbaoxingxi);
            //附件信息
            String[] fjids = shxxHz.getFjids();
            for (int i = 0; i <fjids.length ; i++) {
                fjxxService.updateSbxxIdById(tShenbaoxingxiDto.getId().toString(),fjids[i]);
            }

            // 保存至我的申请业务
            ActBusiness actBusiness = new ActBusiness();
            actBusiness.setUserId(userId);
            actBusiness.setTableId(tShenbaoxingxiDto.getId().toString());
            actBusiness.setProcDefId(shxxHz.getProcDefId());
            actBusiness.setTitle(tShenbaoxingxiDto.getFclx());
            actBusinessService.save(actBusiness);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tShenbaoxingxiDto;
    }

    @Override
    public ShxxHz1 getYeWuShouLiData(Long id) {
        ShxxHz1 shxxHz = new ShxxHz1();
        TShenbaoxingxi shenbaoxingxi = findOneById(id);
        THouseDto houseDto = tHouseService.findById(shenbaoxingxi.getFcid());
        TPersonnelDto personnelDto = tPersonnelService.findById(shenbaoxingxi.getSbrid());
        TEntrustDto entrustDto=null;
        if("1".equals(shenbaoxingxi.getSfwt())){
            entrustDto = entrustService.findById(shenbaoxingxi.getWtxxId());
        }
        assignment(houseDto,personnelDto,shenbaoxingxi,entrustDto,shxxHz);
        return shxxHz;
    }

    //时间戳
    public String getDateStr() {
    	Date date = new Date();
        String str = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

    /**
     *复制属性
     * @param house
     * @param personnel
     * @param tShenbaoxingxi
     * @param shxxHz
     */
    public void copy(THouse house, TPersonnel personnel, TShenbaoxingxi tShenbaoxingxi,TEntrust entrust, ShxxHz shxxHz){
        //房产信息
        house.setId(shxxHz.getFcid());
        house.setJzlx(shxxHz.getFclx());
        house.setFcmj(shxxHz.getFcmj());
        house.setScwz(shxxHz.getScwz());
        house.setZcbz(shxxHz.getZcbz());
        house.setFwlx(shxxHz.getFwlx());

        //申请人信息
        personnel.setId(shxxHz.getSbrid());
        personnel.setTname(shxxHz.getTname());
        personnel.setPhone(shxxHz.getPhone());
        personnel.setCardId(shxxHz.getCardId());
        personnel.setType(shxxHz.getSqrlx());
        personnel.setHjdz(shxxHz.getHjdz());
        personnel.setMark(shxxHz.getMark());

        //审核报表信息
        tShenbaoxingxi.setSbrid(shxxHz.getSbrid());
        tShenbaoxingxi.setFcid(shxxHz.getFcid());
        tShenbaoxingxi.setHtsj(shxxHz.getGfrq());
        tShenbaoxingxi.setWqsj(shxxHz.getGfrq());
        tShenbaoxingxi.setFclx(shxxHz.getFclx());
        tShenbaoxingxi.setFcje(shxxHz.getFcje());
        tShenbaoxingxi.setBtje(shxxHz.getBtje());
        tShenbaoxingxi.setBtye(shxxHz.getBtye());
        tShenbaoxingxi.setSqrlx(shxxHz.getSqrlx());
        //状态（0 未提交 1 已提交 2 审核通过 3 已发放）
        tShenbaoxingxi.setBak1("0");

        if(entrust!=null){
            entrust.setEnterprisename(shxxHz.getEnterpriseName());
            entrust.setPersonname(shxxHz.getPersonName());
            entrust.setTelephone(shxxHz.getTelephone());
            entrust.setCardid(shxxHz.getDbrcardID());
        }

    }

    /**
     * 赋值
     * @param house 房产信息
     * @param personnel 人员信息
     * @param shenbaoxingxi 申报信息
     * @param entrustDto 代办信息
     * @param shxxHz 审核信息汇总
     */
    public void assignment(THouseDto house, TPersonnelDto personnel, TShenbaoxingxi shenbaoxingxi,TEntrustDto entrustDto, ShxxHz1 shxxHz){

        shxxHz.setTname(personnel.getTname());
        shxxHz.setCardId(personnel.getCardId());
        shxxHz.setSqrlx(personnel.getType());
        shxxHz.setPhone(personnel.getPhone());
        shxxHz.setHjdz(personnel.getHjdz());
        shxxHz.setMark(personnel.getMark());
        shxxHz.setFclx(house.getJzlx());
        shxxHz.setGfrq(new SimpleDateFormat("yyyy-MM-dd").format(shenbaoxingxi.getHtsj()));
        shxxHz.setWqrq(new SimpleDateFormat("yyyy-MM-dd").format(shenbaoxingxi.getWqsj()));
        shxxHz.setFcmj(house.getFcmj());
        shxxHz.setFcje(shenbaoxingxi.getFcje());
        shxxHz.setScwz(house.getScwz());
        shxxHz.setZcbz(house.getZcbz());
        shxxHz.setSfwt(shenbaoxingxi.getSfwt());
        shxxHz.setFwlx(house.getFwlx());
        if("1".equals(shenbaoxingxi.getSfwt()) && entrustDto !=null){
            shxxHz.setEnterpriseName(entrustDto.getEnterprisename());
            shxxHz.setPersonName(entrustDto.getPersonname());
            shxxHz.setTelephone(entrustDto.getTelephone());
            shxxHz.setDbrcardID(entrustDto.getCardid());
        }




    }

    //获取编号
    private String getCode(String sqrlx, String fclx) {
        String sqrlxStr="";
        switch (sqrlx){
            case "7":
                //农民工
                sqrlxStr="农民";
                break;
            case "8":
                //退役军人
                sqrlxStr="退役";
                break;
            case "9":
                //高校毕业生
                sqrlxStr="高校";
                break;
            case "10":
                //外地来泸人员
                sqrlxStr="外来";
                break;
            case "11":
                //领办企业
                sqrlxStr="领办";
                break;
        }
        String fclxStr="";
        switch (fclx){
            case "15":
                //新建自住住房
                fclxStr="新建自住";
                break;
            case "14":
                //新建营业用房
                fclxStr="新建营业";
                break;
            case "13":
                //标准化厂房
                fclxStr="标准化厂";
                break;
            case "29":
                //二手自住住房
                fclxStr="二手自住";
                break;
            case "16":
                //一手车位
                fclxStr="一手车位";
                break;
            case "28":
                //二手营业用房
                fclxStr="二手营业";
                break;
        }
        String bh = CodeUtlis.sNumber(CodeUtlis.toFirstChar(sqrlxStr), CodeUtlis.toFirstChar(fclxStr));
        return bh;
    }


    public TShenbaoxingxi findOneById(Long id) {
        TShenbaoxingxi tShenbaoxingxi = tShenbaoxingxiRepository.findById(id).orElseGet(TShenbaoxingxi::new);
        ValidationUtil.isNull(tShenbaoxingxi.getId(),"TShenbaoxingxi","id",id);
        return tShenbaoxingxi;
    }

    @Override
    public List<BusinessTotal> getBusinessTotalData(String startTime, String endTime, String type, String area) throws ParseException {
        //return tShenbaoxingxiRepository.getBusinessTotalData(startTime,endTime,type);
        EntityManager em = entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        StringBuilder sqljoint = new StringBuilder();
        StringBuilder strWhere = new StringBuilder();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotEmpty(startTime)) {
            Date date = new Date(startTime);
            strWhere.append(" AND htsj >= '" + simpleDateFormat.format(date) + "'");
        }

        if (StringUtils.isNotEmpty(endTime)) {
            Date date = new Date(endTime);
            strWhere.append(" AND htsj <= '" + simpleDateFormat.format(date)+ "'");
        }

        if (StringUtils.isNotEmpty(type)) {
            //暂定
        }

        String allWhere = "WHERE 1 = 1";
        if (StringUtils.isNotEmpty(area)) {
            allWhere += " AND area LIKE '%" + area.trim() + "%'";
        }

        sqljoint.append("\n" +
                "SELECT * FROM (\n" +
                "\n" +
                "SELECT '江阳区' AS area\n" +
                ", label\n" +
                ",UUID() AS id\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 13 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type1_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 13 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type1_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 13 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type1_area\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 14 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type2_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 14 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type2_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 14 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type2_area\n" +
                "\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 15 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type3_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 15 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type3_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 15 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type3_area\n" +
                "\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 28 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type4_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 28 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type4_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 28 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type4_area\n" +
                "\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 29 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type5_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 29 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type5_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 29 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type5_area\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 16 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type6_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 16 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type6_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 16 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type6_area\n" +
                "FROM dict_detail WHERE dict_id = 6\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT '龙马潭区'AS area\n" +
                ",label\n" +
                ",UUID() AS id\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 13 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type1_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 13 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type1_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 13 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type1_area\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 14 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type2_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 14 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type2_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 14 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type2_area\n" +
                "\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 15 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type3_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 15 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type3_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 15 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type3_area\n" +
                "\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 28 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type4_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 28 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type4_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 28 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type4_area\n" +
                "\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 29 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type5_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 29 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type5_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 29 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type5_area\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 16 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type6_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 16 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type6_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 16 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type6_area\n" +
                "FROM dict_detail WHERE dict_id = 6\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT '纳溪区'AS area\n" +
                ",label\n" +
                ",UUID() AS id\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 13 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type1_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 13 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type1_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 13 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type1_area\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 14 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type2_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 14 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type2_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 14 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type2_area\n" +
                "\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 15 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type3_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 15 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type3_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 15 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type3_area\n" +
                "\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 28 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type4_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 28 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type4_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 28 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type4_area\n" +
                "\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 29 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type5_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 29 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type5_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 29 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type5_area\n" +
                "\n" +
                ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 16 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type6_tao\n" +
                ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 16 AND sqrlx = dict_detail.id " + strWhere.toString() + ")  AS house_type6_money\n" +
                ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region =128) AND fclx = 16 AND sqrlx = dict_detail.id " + strWhere.toString() + "))  AS house_type6_area\n" +
                "FROM dict_detail WHERE dict_id = 6\n" +
                ") AS a " + allWhere);
        //sqljoint.apped("附加的where子句")
        String sql = sqljoint.toString();
        //em.getTransaction().begin();
        //创建原生查询的时候，将info.class类即第二个参数，写成要传回的bean，这样就可以直接用List<Bean>接收
        Query query1 = em.createNativeQuery(sql, BusinessTotal.class);
        //List<Map<String,Object>> obj=query1.getResultList();
        List<BusinessTotal> obj = query1.getResultList();
        return obj;
    }
}