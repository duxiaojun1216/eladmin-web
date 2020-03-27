package me.zhengjie.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.domain.THouse;
import me.zhengjie.service.THouseService;
import me.zhengjie.service.dto.THouseQueryCriteria;
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
@Api(tags = "房产信息管理")
@RestController
@RequestMapping("/api/tHouse")
public class THouseController {

    private final THouseService tHouseService;

    public THouseController(THouseService tHouseService) {
        this.tHouseService = tHouseService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('tHouse:list')")
    public void download(HttpServletResponse response, THouseQueryCriteria criteria) throws IOException {
        tHouseService.download(tHouseService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询房产信息")
    @ApiOperation("查询房产信息")
    @PreAuthorize("@el.check('tHouse:list')")
    public ResponseEntity<Object> getTHouses(THouseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(tHouseService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增房产信息")
    @ApiOperation("新增房产信息")
    @PreAuthorize("@el.check('tHouse:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody THouse resources){
        return new ResponseEntity<>(tHouseService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改房产信息")
    @ApiOperation("修改房产信息")
    @PreAuthorize("@el.check('tHouse:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody THouse resources){
        tHouseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除房产信息")
    @ApiOperation("删除房产信息")
    @PreAuthorize("@el.check('tHouse:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        tHouseService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}