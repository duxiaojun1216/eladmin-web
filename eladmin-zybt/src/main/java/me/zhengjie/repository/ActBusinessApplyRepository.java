package me.zhengjie.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import me.zhengjie.base.XbootBaseDao;
import me.zhengjie.domain.TShenbaoxingxi;

/**
 * 流程分类数据处理层
 * @author Exrick
 */
public interface ActBusinessApplyRepository extends XbootBaseDao<TShenbaoxingxi, String> {

    /**
     * 通过id获取
     * @param parentId
     * @return
     */
	@Query(value = "SELECT 	t4.label,t5.label AS fcType, t1.phone,t1.tname,t1.type,t1.card_id,t1.hjdz,t2.cqnx,t2.kfs,t2.fcmj,t2.jcsj,t2.scwz,t2.jylx,t3.enterprisename,t3.personname,t3.telephone,t3.cardid as enterpriseCardiId,t.* FROM t_shenbaoxingxi t LEFT JOIN t_personnel t1 ON t.sbrid = t1.id LEFT JOIN t_house t2 ON t.fcid = t2.id LEFT JOIN t_entrust t3 ON t.wtxx_Id = t3.id LEFT JOIN dict_detail t4 ON t1.type = t4.id LEFT JOIN dict_detail t5 ON t2.jzlx = t5.id where t.id = ?1",nativeQuery = true)
    Map<String, Object> findByIdOrderBySortOrder(String Id);

}