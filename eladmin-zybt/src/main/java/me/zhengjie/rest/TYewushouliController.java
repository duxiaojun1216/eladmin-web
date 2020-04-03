package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.common.utils.SecurityUtil;
import me.zhengjie.service.dto.*;
import me.zhengjie.system.service.dto.UserDto;
import me.zhengjie.utils.CodeUtlis;
import me.zhengjie.domain.THouse;
import me.zhengjie.domain.TPersonnel;
import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.service.THouseService;
import me.zhengjie.service.TPersonnelService;
import me.zhengjie.service.TShenbaoxingxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zengjian
 * @date 2020-03-23
 */
@Api(tags = "申报信息管理")
@RestController
@RequestMapping("/api/yewushouli")
public class TYewushouliController {
    @Autowired
    private  TShenbaoxingxiService tShenbaoxingxiService;
    @Autowired
    private  TPersonnelService tPersonnelService;
    @Autowired
    private  THouseService tHouseService;
    @Autowired
    private SecurityUtil securityUtil;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('yewushouli:list')")
    public void download(HttpServletResponse response, TShenbaoxingxiQueryCriteria criteria) throws IOException {
        tShenbaoxingxiService.download(tShenbaoxingxiService.queryAll(criteria), response);
    }

    @GetMapping
    //@Log("查询申报信息")
    @ApiOperation("查询申报信息")
   // @PreAuthorize("@el.check('tShenbaoxingxi:list')")
    public ResponseEntity<Object> getTShenbaoxingxis(TShenbaoxingxiQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tShenbaoxingxiService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增申报信息")
    @ApiOperation("新增申报信息")
    @PreAuthorize("@el.check('yewushouli:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ShxxHz shxxHz){
        System.out.println(shxxHz);
        //房产信息
        THouse tHouse = new THouse();
        //申请人信息
        TPersonnel tPersonnel = new TPersonnel();
        //审核报表信息
        TShenbaoxingxi tShenbaoxingxi = new TShenbaoxingxi();
        // 获取登录人 设置创建人 时间
        String username = securityUtil.getCurrUser().getUsername().toString();
        tPersonnel.setCreateid(username);

        tPersonnel.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tHouse.setCreateId(username);

        tHouse.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tShenbaoxingxi.setCreateId(username);
        tShenbaoxingxi.setCreateTime(new Timestamp(System.currentTimeMillis()));

        Result result = new Result();

        copy(tHouse,tPersonnel,tShenbaoxingxi,shxxHz);
        try {
            THouseDto tHouseDto = tHouseService.create(tHouse);
            tShenbaoxingxi.setFcid(tHouseDto.getId());

            TPersonnelDto tPersonnelDto = tPersonnelService.create(tPersonnel);
            tShenbaoxingxi.setSbrid(tPersonnelDto.getId());

            //批次号
            String pch = CodeUtlis.sNumber(tShenbaoxingxi.getSqrlx(), tShenbaoxingxi.getFclx());
            tShenbaoxingxi.setPch(pch);
            TShenbaoxingxiDto tShenbaoxingxiDto = tShenbaoxingxiService.create(tShenbaoxingxi);


            result.setSbxxid(tShenbaoxingxiDto.getId());
            result.setCode(tShenbaoxingxiDto.getPch());
            result.setSuccess(true);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,HttpStatus.CREATED);

    }

    @PutMapping
    @Log("修改申报信息")
    @ApiOperation("修改申报信息")
    @PreAuthorize("@el.check('yewushouli:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ShxxHz shxxHz){
        //房产信息
        THouse tHouse = new THouse();
        //申请人信息
        TPersonnel tPersonnel = new TPersonnel();
        //审核报表信息
        TShenbaoxingxi tShenbaoxingxi = new TShenbaoxingxi();

        // 获取登录人 设置修改人 时间
        String username = securityUtil.getCurrUser().getUsername();


        tPersonnel.setUpdateId(username);
        tPersonnel.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        tHouse.setUpdateId(username);
        tHouse.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        tShenbaoxingxi.setUpdateId(username);
        tShenbaoxingxi.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //todo 获取用户信息保存部门id
        tShenbaoxingxi.setDepId(1l);

        tHouseService.update(tHouse);
        tPersonnelService.update(tPersonnel);
        tShenbaoxingxiService.update(tShenbaoxingxi);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除申报信息")
    @ApiOperation("删除申报信息")
    @PreAuthorize("@el.check('yewushouli:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        tShenbaoxingxiService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("上传附件信息")
    @ApiOperation("上传附件")
//    @PreAuthorize("@   .check('yewushouli:uploadFj')")
    @RequestMapping(value = "/uploadFj", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadFj(@RequestParam(required = false) MultipartFile[] file
                                          ,@RequestParam(required = false) EnclosureParameter fileDtata) throws IOException{

        String idStr=null;
        if(securityUtil!=null){
            //todo 先设置为 固定的 test
            idStr = securityUtil.getCurrUser().getId().toString();
            if(fileDtata.getIdStr()==null){
                fileDtata.setIdStr(idStr);
            }
        }

        List<TFjxxDto> fjxxDtos = tShenbaoxingxiService.uploadFj(file,fileDtata);
        return new ResponseEntity<>(fjxxDtos,HttpStatus.CREATED);
    }




    public void copy(THouse house,TPersonnel personnel,TShenbaoxingxi tShenbaoxingxi,ShxxHz shxxHz){
        //房产信息
        house.setId(shxxHz.getFcid());
        house.setJzlx(shxxHz.getFclx());
//        house.setCqnx(shxxHz.getCqnx());
//        house.setKfs(shxxHz.getKfs());
        house.setFcmj(shxxHz.getFcmj());
//        house.setJcsj(shxxHz.getJcsj());
        house.setScwz(shxxHz.getScwz());

        //申请人信息
        personnel.setId(shxxHz.getSbrid());
        personnel.setTname(shxxHz.getTname());
        personnel.setPhone(shxxHz.getPhone());
        personnel.setCardId(shxxHz.getCardId());
        personnel.setType(shxxHz.getSqrlx());

        //审核报表信息
        tShenbaoxingxi.setSbrid(shxxHz.getSbrid());
        tShenbaoxingxi.setFcid(shxxHz.getFcid());
        tShenbaoxingxi.setHtsj(shxxHz.getGfrq());
        tShenbaoxingxi.setWqsj(shxxHz.getWqrq());
        tShenbaoxingxi.setFclx(shxxHz.getFclx());
        tShenbaoxingxi.setFcje(shxxHz.getFcje());
        tShenbaoxingxi.setBtje(shxxHz.getBtje());
        tShenbaoxingxi.setBtye(shxxHz.getBtye());
        tShenbaoxingxi.setSqrlx(shxxHz.getSqrlx());

    }
}