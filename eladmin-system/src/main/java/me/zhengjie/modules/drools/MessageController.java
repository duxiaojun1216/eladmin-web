package me.zhengjie.modules.drools;

import java.text.ParseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhengjie.aop.log.Log;
import me.zhengjie.config.drools.DroolsUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试")
@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Log("测试")
	@GetMapping(value = "/test",produces = "text/html;charset=UTF-8;")
    @ApiOperation("测试")
    public String test() throws ParseException {
        double resultMoney = DroolsUtils.getResultMoney(4, "01-09-2020", "28-02-2021", "15-10-2020", 800000);
        return "补助金额为：" + resultMoney;
    }
}
