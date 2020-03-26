package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.domain.TBzwh;
import me.zhengjie.service.TBzwhService;
import me.zhengjie.service.dto.TBzwhQueryCriteria;
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
* @author dengjie
* @date 2020-03-26
*/
@Api(tags = "t_bzwh管理")
@RestController
@RequestMapping("/api/tBzwh")
public class TBzwhController {

    private final TBzwhService tBzwhService;

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

    @GetMapping
    @Log("查询t_bzwh")
    @ApiOperation("查询t_bzwh")
    @PreAuthorize("@el.check('tBzwh:list')")
    public ResponseEntity<Object> getTBzwhs(TBzwhQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tBzwhService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增t_bzwh")
    @ApiOperation("新增t_bzwh")
    @PreAuthorize("@el.check('tBzwh:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody TBzwh resources){
        return new ResponseEntity<>(tBzwhService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改t_bzwh")
    @ApiOperation("修改t_bzwh")
    @PreAuthorize("@el.check('tBzwh:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody TBzwh resources){
        tBzwhService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除t_bzwh")
    @ApiOperation("删除t_bzwh")
    @PreAuthorize("@el.check('tBzwh:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        tBzwhService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}