package test.rest;

import me.zhengjie.aop.log.Log;
import test.domain.Dict;
import test.service.DictService;
import test.service.dto.DictQueryCriteria;
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
* @author wangwei
* @date 2020-03-28
*/
@Api(tags = "test管理")
@RestController
@RequestMapping("/api/dict")
public class DictController {

    private final DictService dictService;

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('dict:list')")
    public void download(HttpServletResponse response, DictQueryCriteria criteria) throws IOException {
        dictService.download(dictService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询test")
    @ApiOperation("查询test")
    @PreAuthorize("@el.check('dict:list')")
    public ResponseEntity<Object> getDicts(DictQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(dictService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增test")
    @ApiOperation("新增test")
    @PreAuthorize("@el.check('dict:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Dict resources){
        return new ResponseEntity<>(dictService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改test")
    @ApiOperation("修改test")
    @PreAuthorize("@el.check('dict:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Dict resources){
        dictService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除test")
    @ApiOperation("删除test")
    @PreAuthorize("@el.check('dict:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        dictService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}