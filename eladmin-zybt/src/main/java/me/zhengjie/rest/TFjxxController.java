package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.domain.TFjxx;
import me.zhengjie.service.TFjxxService;
import me.zhengjie.service.dto.TFjxxQueryCriteria;
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
* @date 2020-03-27
*/
@Api(tags = "附件信息管理")
@RestController
@RequestMapping("/api/tFjxx")
public class TFjxxController {

    private final TFjxxService tFjxxService;

    public TFjxxController(TFjxxService tFjxxService) {
        this.tFjxxService = tFjxxService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tFjxx:list')")
    public void download(HttpServletResponse response, TFjxxQueryCriteria criteria) throws IOException {
        tFjxxService.download(tFjxxService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询附件信息")
    @ApiOperation("查询附件信息")
    @PreAuthorize("@el.check('tFjxx:list')")
    public ResponseEntity<Object> getTFjxxs(TFjxxQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tFjxxService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增附件信息")
    @ApiOperation("新增附件信息")
    @PreAuthorize("@el.check('tFjxx:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody TFjxx resources){
        return new ResponseEntity<>(tFjxxService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改附件信息")
    @ApiOperation("修改附件信息")
    @PreAuthorize("@el.check('tFjxx:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody TFjxx resources){
        tFjxxService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除附件信息")
    @ApiOperation("删除附件信息")
    @PreAuthorize("@el.check('tFjxx:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        tFjxxService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}