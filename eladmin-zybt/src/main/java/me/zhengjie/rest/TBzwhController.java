package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.common.utils.ResultUtil;
import me.zhengjie.common.utils.SecurityUtil;
import me.zhengjie.common.vo.PageVo;
import me.zhengjie.common.vo.Result;
import me.zhengjie.domain.TBzwh;
import me.zhengjie.service.TBzwhService;
import me.zhengjie.service.dto.TBzwhQueryCriteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletResponse;

/**
* @author dengjie
* @date 2020-04-01
*/
@Api(tags = "t_bzwh管理")
@RestController
@RequestMapping("/api/tBzwh")
public class TBzwhController {

    private final TBzwhService tBzwhService;
    @Autowired
    private SecurityUtil securityUtil;

    public TBzwhController(TBzwhService tBzwhService) {
        this.tBzwhService = tBzwhService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tBzwh:list')")
    public void download(HttpServletResponse response, TBzwhQueryCriteria criteria) throws IOException {
        tBzwhService.download(tBzwhService.queryAll(criteria), response);
    }

    @GetMapping(value = "/getTBzwhs")
    @Log("查询t_bzwh")
    @ApiOperation("查询t_bzwh")
    @PreAuthorize("@el.check('tBzwh:list')")
    public ResponseEntity<Object> getTBzwhs(TBzwhQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tBzwhService.queryAll(criteria,pageable),HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getTBzwhsBypage", method = RequestMethod.GET)
    //@Log("查询t_bzwh")
    @ApiOperation("查询t_bzwh")
//    @PreAuthorize("@el.check('tBzwh:list')")
    public Result<Object> getTBzwhsBypage(TBzwhQueryCriteria criteria, PageVo pageVo){
    	Sort s = new Sort(Sort.Direction.DESC, "id");
    	Pageable pagealeble =  PageRequest.of(pageVo.getPageNumber()-1,pageVo.getPageSize(),s);
    	criteria  = new TBzwhQueryCriteria();
        return ResultUtil.data(tBzwhService.queryAll(criteria,pagealeble));
    }
    @RequestMapping(value = "/getTBzwhsById", method = RequestMethod.GET)
    //@Log("查询t_bzwh")
    @ApiOperation("查询t_bzwh")
//    @PreAuthorize("@el.check('tBzwh:list')")
    public Result<Object> getTBzwhsById(int id){    	
        return ResultUtil.data(tBzwhService.findById(id));
    }

    @PostMapping
    @Log("新增t_bzwh")
    @ApiOperation("新增t_bzwh")
    @PreAuthorize("@el.check('tBzwh:add')")
    public Result<Object>  create(@Validated @RequestBody TBzwh resources){
    	String userId=securityUtil.getCurrUser().getId().toString();
    	resources.setCreateId(userId);
    	resources.setCreateTime(new Timestamp(System.currentTimeMillis()));   	
        return ResultUtil.data(tBzwhService.create(resources));
    }

    @PutMapping
    @Log("修改t_bzwh")
    @ApiOperation("修改t_bzwh")
    @PreAuthorize("@el.check('tBzwh:edit')")
    public Result<Object>  update(@Validated @RequestBody TBzwh resources){
    	String userId=securityUtil.getCurrUser().getId().toString();
   	 	resources.setUpdateId(userId);
   	 	resources.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        tBzwhService.update(resources);
        return ResultUtil.success("操作成功");
    }

    @Log("删除t_bzwh")
    @ApiOperation("删除t_bzwh")
    @PreAuthorize("@el.check('tBzwh:del')")
    @DeleteMapping
    public Result<Object> deleteAll(@RequestBody Integer[] ids) {
        tBzwhService.deleteAll(ids);
        return ResultUtil.success("操作成功");
    }
}