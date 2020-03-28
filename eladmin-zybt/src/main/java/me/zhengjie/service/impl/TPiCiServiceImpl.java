package me.zhengjie.service.impl;

import me.zhengjie.service.TPiCiService;
import me.zhengjie.system.service.DeptService;
import me.zhengjie.system.service.dto.DeptDto;
import me.zhengjie.utils.CodeUtlis;
import org.springframework.stereotype.Service;

@Service
public class TPiCiServiceImpl implements TPiCiService {

    private final DeptService deptService;

    public TPiCiServiceImpl(DeptService deptService) {
        this.deptService = deptService;
    }


    @Override
    public String selectPiCi(Long id) {
        DeptDto deptDto = deptService.findById(id);
        DeptDto deptDto1 = deptService.findById(deptDto.getPid());
        String name = deptDto1.getName();

        return  CodeUtlis.batch(name);
    }
}

