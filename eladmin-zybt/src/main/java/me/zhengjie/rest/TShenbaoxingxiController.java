package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.service.TShenbaoxingxiService;
import me.zhengjie.service.dto.TShenbaoxingxiQueryCriteria;
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
* @author zengjian
* @date 2020-03-25
*/
@Api(tags = "申报信息管理")
@RestController
@RequestMapping("/api/tShenbaoxingxi")
public class TShenbaoxingxiController {

    private final TShenbaoxingxiService tShenbaoxingxiService;

    public TShenbaoxingxiController(TShenbaoxingxiService tShenbaoxingxiService) {
        this.tShenbaoxingxiService = tShenbaoxingxiService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tShenbaoxingxi:list')")
    public void download(HttpServletResponse response, TShenbaoxingxiQueryCriteria criteria) throws IOException {
        tShenbaoxingxiService.download(tShenbaoxingxiService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询申报信息")
    @ApiOperation("查询申报信息")
    @PreAuthorize("@el.check('tShenbaoxingxi:list')")
    public ResponseEntity<Object> getTShenbaoxingxis(TShenbaoxingxiQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tShenbaoxingxiService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增申报信息")
    @ApiOperation("新增申报信息")
    @PreAuthorize("@el.check('tShenbaoxingxi:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody TShenbaoxingxi resources){
        return new ResponseEntity<>(tShenbaoxingxiService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改申报信息")
    @ApiOperation("修改申报信息")
    @PreAuthorize("@el.check('tShenbaoxingxi:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody TShenbaoxingxi resources){
        tShenbaoxingxiService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除申报信息")
    @ApiOperation("删除申报信息")
    @PreAuthorize("@el.check('tShenbaoxingxi:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        tShenbaoxingxiService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}