package me.zhengjie.service.impl;

import java.util.List;
import java.util.Map;






import lombok.extern.slf4j.Slf4j;
import me.zhengjie.base.XbootBaseDao;
import me.zhengjie.common.utils.SecurityUtil;


import me.zhengjie.entity.ActBusiness;
import me.zhengjie.repository.ActBusinessApplyRepository;
import me.zhengjie.service.ActBusinessApplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 业务申请接口实现
 * @author Exrick
 */
@Slf4j
@Service
@Transactional
public class ActBusinessApplyServiceImpl implements ActBusinessApplyService {

    @Autowired
    private ActBusinessApplyRepository actBusinessDao;


	@Override
	public ActBusinessApplyRepository getRepository() {
		return actBusinessDao;
	}

	@Override
	public Map<String, Object> findApplyById(String Id) {
		return actBusinessDao.findByIdOrderBySortOrder(Id);
	}

   
}