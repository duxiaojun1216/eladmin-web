package me.zhengjie.rest.business;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.base.XbootBaseController;
import me.zhengjie.common.utils.ResultUtil;
import me.zhengjie.common.utils.SecurityUtil;
import me.zhengjie.common.vo.Result;
import me.zhengjie.entity.business.Leave;
import me.zhengjie.service.*;
import me.zhengjie.service.business.LeaveService;
import me.zhengjie.service.dto.BusinessTotal;
import me.zhengjie.service.dto.ShxxHz;
import me.zhengjie.service.dto.ShxxHz1;
import me.zhengjie.service.dto.TShenbaoxingxiDto;
import me.zhengjie.system.service.dto.DeptSmallDto;
import me.zhengjie.utils.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

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

        TShenbaoxingxiDto tShenbaoxingxiDto=null;
        if(shxxHz.getSbid() !=null){
            tShenbaoxingxiService.updateShenBaoXinXi(shxxHz);
        }else {
            tShenbaoxingxiService.addShenBaoXinXi(shxxHz);
        }
        return (new ResultUtil()).setData(null);
    }



    @RequestMapping(value = {"/getYeWuShouLiData/{id}"}, method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation("通过id获取业务受理表单信息")
    public Result<ShxxHz> getYeWuShouLiData(@ApiParam("申报信息id")@PathVariable Long id) {
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


    /**
     * 查询页面table数据 统计分析
     * @param startTime
     * @param endTime
     * @param type
     * @param area
     * @param response
     * @return
     * @throws ParseException
     */
    @GetMapping("/getBusinessTotalData")
    public Result<Object> getBusinessTotalData(@ApiParam("时间段") @RequestParam(required = false) String startTime
            , @ApiParam("时间段") @RequestParam(required = false) String endTime
            , @ApiParam("类型") @RequestParam(required = false) String type
            , @ApiParam("区县") @RequestParam(required = false) String area, HttpServletResponse response
    ) throws ParseException {

        List<BusinessTotal> businessTotalData = tShenbaoxingxiService.getBusinessTotalData(startTime, endTime, type,area);
        return new ResultUtil<>().setData(businessTotalData);
    }


    /**
     * 根据条件导出excel   统计分析
     * @param startTime
     * @param endTime
     * @param type
     * @param area
     * @param response
     * @throws Exception
     */
    @GetMapping(value = "/exportExcel", produces = "text/html;charset=UTF-8;")
    public void exportExcel(@ApiParam("时间段") @RequestParam(required = false) String startTime
            , @ApiParam("时间段") @RequestParam(required = false) String endTime
            , @ApiParam("类型") @RequestParam(required = false) String type
            , @ApiParam("区县") @RequestParam(required = false) String area, HttpServletResponse response) throws Exception {
        String title = "泸州市置业补助统计报表";
        List<BusinessTotal> businessTotalData = tShenbaoxingxiService.getBusinessTotalData(startTime, endTime, type,area);
        ExportExcel ex = new ExportExcel(title, businessTotalData);
        ex.exportExcelData(response);
    }
}
