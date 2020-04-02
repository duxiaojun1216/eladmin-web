package me.zhengjie.service;

import java.util.List;
import java.util.Map;

import me.zhengjie.base.XbootBaseService;
import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.entity.ActBusiness;
import me.zhengjie.entity.ActCategory;

public interface ActBusinessApplyService extends XbootBaseService<TShenbaoxingxi, String> {

    /**
     * 通过id获取
     * @param parentId
     * @return
     */
   Map<String, Object> findApplyById(String Id);

}