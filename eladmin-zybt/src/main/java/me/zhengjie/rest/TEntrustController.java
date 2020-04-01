package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.domain.TEntrust;
import me.zhengjie.service.TEntrustService;
import me.zhengjie.service.dto.TEntrustQueryCriteria;
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
* @date 2020-04-01
*/
@Api(tags = "委托信息管理")
@RestController
@RequestMapping("/api/tEntrust")
public class TEntrustController {

    private final TEntrustService tEntrustService;

    public TEntrustController(TEntrustService tEntrustService) {
        this.tEntrustService = tEntrustService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tEntrust:list')")
    public void download(HttpServletResponse response, TEntrustQueryCriteria criteria) throws IOException {
        tEntrustService.download(tEntrustService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询委托信息")
    @ApiOperation("查询委托信息")
    @PreAuthorize("@el.check('tEntrust:list')")
    public ResponseEntity<Object> getTEntrusts(TEntrustQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tEntrustService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增委托信息")
    @ApiOperation("新增委托信息")
    @PreAuthorize("@el.check('tEntrust:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody TEntrust resources){
        return new ResponseEntity<>(tEntrustService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改委托信息")
    @ApiOperation("修改委托信息")
    @PreAuthorize("@el.check('tEntrust:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody TEntrust resources){
        tEntrustService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除委托信息")
    @ApiOperation("删除委托信息")
    @PreAuthorize("@el.check('tEntrust:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        tEntrustService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}