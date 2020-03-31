package me.zhengjie.rest;

import me.zhengjie.common.utils.SecurityUtil;
import me.zhengjie.service.TPiCiService;
import me.zhengjie.system.service.UserService;
import me.zhengjie.system.service.dto.UserDto;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tpici")
public class TPiCiController {

    @Autowired
    private TPiCiService tPiCiService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/pici")
    public String PiCi(){
        ///SecurityUtil securityUtil = new SecurityUtil();
        //获取当前用户id
       // UserDto user =  securityUtil.getCurrUser();
        //UserDto user = userService.findByName(SecurityUtils.getUsername());
        Long id = userService.findByName(SecurityUtils.getUsername()).getDept().getId();
        //根据id查询生成时间区域批次字符串
        return tPiCiService.selectPiCi(id);
    }

}

