package test.rest;

import me.zhengjie.aop.log.Log;
import test.domain.Test;
import test.service.TestService;
import test.service.dto.TestQueryCriteria;
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
* @date 2020-02-29
*/
@Api(tags = "test管理")
@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('test:list')")
    public void download(HttpServletResponse response, TestQueryCriteria criteria) throws IOException {
        testService.download(testService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询test")
    @ApiOperation("查询test")
    @PreAuthorize("@el.check('test:list')")
    public ResponseEntity<Object> getTests(TestQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(testService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增test")
    @ApiOperation("新增test")
    @PreAuthorize("@el.check('test:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Test resources){
        return new ResponseEntity<>(testService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改test")
    @ApiOperation("修改test")
    @PreAuthorize("@el.check('test:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Test resources){
        testService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除test")
    @ApiOperation("删除test")
    @PreAuthorize("@el.check('test:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        testService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}