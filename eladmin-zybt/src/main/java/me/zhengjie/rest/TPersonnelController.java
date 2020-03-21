package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.domain.TPersonnel;
import me.zhengjie.service.TPersonnelService;
import me.zhengjie.service.dto.TPersonnelQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author zengjina
* @date 2020-03-21
*/
@Api(tags = "申报人员信息管理")
@RestController
@RequestMapping("/api/tPersonnel")
public class TPersonnelController {

    private final TPersonnelService tPersonnelService;

    public TPersonnelController(TPersonnelService tPersonnelService) {
        this.tPersonnelService = tPersonnelService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tPersonnel:list')")
    public void download(HttpServletResponse response, TPersonnelQueryCriteria criteria) throws IOException {
        tPersonnelService.download(tPersonnelService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询申报人员信息")
    @ApiOperation("查询申报人员信息")
    @PreAuthorize("@el.check('tPersonnel:list')")
    public ResponseEntity<Object> getTPersonnels(TPersonnelQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tPersonnelService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增申报人员信息")
    @ApiOperation("新增申报人员信息")
    @PreAuthorize("@el.check('tPersonnel:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody TPersonnel resources){
        return new ResponseEntity<>(tPersonnelService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改申报人员信息")
    @ApiOperation("修改申报人员信息")
    @PreAuthorize("@el.check('tPersonnel:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody TPersonnel resources){
        tPersonnelService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除申报人员信息")
    @ApiOperation("删除申报人员信息")
    @PreAuthorize("@el.check('tPersonnel:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        tPersonnelService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}