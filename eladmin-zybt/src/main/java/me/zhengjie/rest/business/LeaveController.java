package me.zhengjie.rest.business;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.base.XbootBaseController;
import me.zhengjie.common.utils.ResultUtil;
import me.zhengjie.common.utils.SecurityUtil;
import me.zhengjie.common.vo.PageVo;
import me.zhengjie.common.vo.Result;
import me.zhengjie.common.vo.SearchVo;
import me.zhengjie.domain.THouse;
import me.zhengjie.domain.TPersonnel;
import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.entity.ActBusiness;
import me.zhengjie.entity.business.Leave;
import me.zhengjie.service.*;
import me.zhengjie.service.business.LeaveService;
import me.zhengjie.service.dto.*;
import me.zhengjie.system.service.dto.DeptSmallDto;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author Exrickx
 */
@Slf4j
@RestController
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

        TShenbaoxingxiDto tShenbaoxingxiDto = tShenbaoxingxiService.addShenBaoXinXi(shxxHz);
        return (new ResultUtil()).setData(tShenbaoxingxiDto);
    }



    @RequestMapping(value = {"/getYeWuShouLiData/{id}"}, method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation("通过id获取业务受理表单信息")
    public Result<ShxxHz> getYeWuShouLiData(@PathVariable Long id) {
        ShxxHz1 yeWuShouLiData = tShenbaoxingxiService.getYeWuShouLiData(id);
        return (new ResultUtil()).setData(yeWuShouLiData);

    }


    /**
     * 获取当前用户的权限
     */
    @RequestMapping(value = "/getPer")
    @GetMapping
    @ResponseBody
    public String jurisdiction(){
        DeptSmallDto dept = securityUtil.getCurrUser().getDept();
        //将部门转换为拼音首字母并返回
        String batch =me.zhengjie.utils.CodeUtlis.toFirstChar(dept.getName());
        return batch;
    }

    @GetMapping("/getBusinessTotalData")
    public Result<Object> getBusinessTotalData(@ApiParam("时间段") @RequestParam(required = false) String startTime
            , @ApiParam("时间段") @RequestParam(required = false) String endTime
            , @ApiParam("类型") @RequestParam(required = false) String type
            , SearchVo searchVo, PageVo pageVo) throws ParseException {

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date startParse = simpleDateFormat.parse(startTime);
//        Date endParse = simpleDateFormat.parse(endTime);

//        List<BusinessTotal> businessTotalData = tShenbaoxingxiService.getBusinessTotalData(startTime, endTime, type);

        List<Map<String, String>> businessTotalData = tShenbaoxingxiService.getBusinessTotalData(startTime, endTime, type);
        return new ResultUtil<>().setData(businessTotalData);
    }
}
