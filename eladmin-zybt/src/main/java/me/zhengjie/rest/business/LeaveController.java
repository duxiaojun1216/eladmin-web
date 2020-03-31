package me.zhengjie.rest.business;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.base.XbootBaseController;
import me.zhengjie.common.utils.ResultUtil;
import me.zhengjie.common.utils.SecurityUtil;
import me.zhengjie.common.vo.Result;
import me.zhengjie.domain.THouse;
import me.zhengjie.domain.TPersonnel;
import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.entity.ActBusiness;
import me.zhengjie.entity.business.Leave;
import me.zhengjie.service.*;
import me.zhengjie.service.business.LeaveService;

import me.zhengjie.service.dto.*;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author Exrickx
 */
@Slf4j
@RestController
@Api(description = "请假申请接口")
@Transactional
@RequestMapping(value = "/api/leave")
public class LeaveController extends XbootBaseController<Leave, String> {

    @Autowired
    private TShenbaoxingxiService tShenbaoxingxiService;
    @Autowired
    private TPersonnelService tPersonnelService;
    @Autowired
    private THouseService tHouseService;

    @Autowired
    private TFjxxService fjxxService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private ActBusinessService actBusinessService;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public LeaveService getService() {
        return leaveService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加申请草稿状态")
    public Result<Object> add(@Validated @RequestBody ShxxHz shxxHz){

        //房产信息
        THouse tHouse = new THouse();
        //申请人信息
        TPersonnel tPersonnel = new TPersonnel();
        //审核报表信息
        TShenbaoxingxi tShenbaoxingxi = new TShenbaoxingxi();
        // 获取登录人 设置创建人 时间
        String username = SecurityUtils.getUsername();
        tPersonnel.setCreateid(username);

        tPersonnel.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tHouse.setCreateId(username);

        tHouse.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tShenbaoxingxi.setCreateId(username);
        tShenbaoxingxi.setCreateTime(new Timestamp(System.currentTimeMillis()));

        me.zhengjie.service.dto.Result result = new me.zhengjie.service.dto.Result();

        copy(tHouse,tPersonnel,tShenbaoxingxi,shxxHz);
        String str=new SimpleDateFormat("yyyy-MM-dd").format(shxxHz.getWqrq());
        try {
            //申报人员 信息
            TPersonnelDto tPersonnelDto = tPersonnelService.create(tPersonnel);
            tShenbaoxingxi.setSbrid(tPersonnelDto.getId());

            //申报房产信息
            THouseDto tHouseDto = tHouseService.create(tHouse);
            tShenbaoxingxi.setFcid(tHouseDto.getId());

            //批次号 申报表单信息
            String pch = CodeUtlis.sNumber(tShenbaoxingxi.getSqrlx(), tShenbaoxingxi.getFclx());
            tShenbaoxingxi.setPch(pch);
            TShenbaoxingxiDto tShenbaoxingxiDto = tShenbaoxingxiService.create(tShenbaoxingxi);

            //附件信息
            for(long id :shxxHz.getFjids()){
                fjxxService.updateSbxxIdById(tShenbaoxingxiDto.getId().toString(),id);
            }

            result.setSbxxid(tShenbaoxingxiDto.getId());
            result.setCode(tShenbaoxingxiDto.getPch());
            result.setSuccess(true);

            // 保存至我的申请业务
            String userId = securityUtil.getCurrUser().getId().toString();
            ActBusiness actBusiness = new ActBusiness();
            actBusiness.setUserId(userId);
            actBusiness.setTableId(tShenbaoxingxiDto.getId().toString());
            actBusiness.setProcDefId(shxxHz.getProcDefId());
            actBusiness.setTitle(tShenbaoxingxiDto.getFclx());
            actBusinessService.save(actBusiness);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return ResultUtil.data(null);
    }

    /**
     *复制属性
     * @param house
     * @param personnel
     * @param tShenbaoxingxi
     * @param shxxHz
     */
    public void copy(THouse house, TPersonnel personnel, TShenbaoxingxi tShenbaoxingxi, ShxxHz shxxHz){
        //房产信息
        house.setId(shxxHz.getFcid());
        house.setJzlx(shxxHz.getFclx());
//        house.setCqnx(shxxHz.getCqnx());
//        house.setKfs(shxxHz.getKfs());
        house.setFcmj(shxxHz.getFcmj());
//        house.setJcsj(shxxHz.getJcsj());
        house.setScwz(shxxHz.getScwz());
        house.setJylx(shxxHz.getJylx());
        house.setZcbz(shxxHz.getZcbz());

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

    }



    @RequestMapping(
            value = {"/getYeWuShouLiData/{id}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    @ApiOperation("通过id获取")
    public Result<ShxxHz> getYeWuShouLiData(@PathVariable Long id) {
        ShxxHz1 shxxHz = new ShxxHz1();
        TShenbaoxingxiDto shenbaoxingxiDto = tShenbaoxingxiService.findById(id);
        THouseDto houseDto = tHouseService.findById(shenbaoxingxiDto.getFcid());
        TPersonnelDto personnelDto = tPersonnelService.findById(shenbaoxingxiDto.getSbrid());
        assignment(houseDto,personnelDto,shenbaoxingxiDto,shxxHz);
        System.out.println(shxxHz);
        return (new ResultUtil()).setData(shxxHz);

    }

    /**
     * 赋值
     * @param house
     * @param personnel
     * @param shenbaoxingxi
     * @param shxxHz
     */
    public void assignment(THouseDto house, TPersonnelDto personnel, TShenbaoxingxiDto shenbaoxingxi, ShxxHz1 shxxHz){

        shxxHz.setTname(personnel.getTname());
        shxxHz.setCardId(personnel.getCardId());
        shxxHz.setSqrlx(personnel.getType());
        shxxHz.setPhone(personnel.getPhone());
        shxxHz.setHjdz(personnel.getHjdz());
        shxxHz.setMark(personnel.getMark());
        shxxHz.setFclx(house.getJzlx());
        shxxHz.setJylx(house.getJylx());
        shxxHz.setGfrq(new SimpleDateFormat("yyyy-MM-dd").format(shenbaoxingxi.getHtsj()));
        shxxHz.setWqrq(new SimpleDateFormat("yyyy-MM-dd").format(shenbaoxingxi.getWqsj()));
        shxxHz.setFcmj(house.getFcmj());
        shxxHz.setFcje(shenbaoxingxi.getFcje());
        shxxHz.setScwz(house.getScwz());
        shxxHz.setZcbz(house.getZcbz());



    }
}
