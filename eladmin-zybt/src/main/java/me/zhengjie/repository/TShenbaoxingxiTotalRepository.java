package me.zhengjie.repository;

import me.zhengjie.domain.TShenbaoxingxi;
import me.zhengjie.service.dto.BusinessTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zengjian
 * @date 2020-03-25
 */
public interface TShenbaoxingxiTotalRepository extends JpaRepository<TShenbaoxingxi, BusinessTotal>, JpaSpecificationExecutor<TShenbaoxingxi> {

    @Transactional
    @Query(value = "\n" +
            "SELECT * FROM (\n" +
            "\n" +
            "SELECT '江阳区' AS area\n" +
            ",label\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 13)  AS house_type1_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 13)  AS house_type1_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 13))  AS house_type1_area\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 14)  AS house_type2_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 14)  AS house_type2_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 14))  AS house_type2_area\n" +
            "\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 15)  AS house_type3_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 15)  AS house_type3_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 15))  AS house_type3_area\n" +
            "\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 28)  AS house_type4_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 28)  AS house_type4_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 28))  AS house_type4_area\n" +
            "\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 29)  AS house_type5_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 29)  AS house_type5_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 29))  AS house_type5_area\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 16)  AS house_type6_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 16)  AS house_type6_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 7) AND fclx = 16))  AS house_type6_area\n" +
            "FROM dict_detail WHERE dict_id = 6\n" +
            "\n" +
            "UNION ALL\n" +
            "\n" +
            "SELECT '龙马潭区'AS area\n" +
            ",label\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 13)  AS house_type1_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 13)  AS house_type1_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 13))  AS house_type1_area\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 14)  AS house_type2_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 14)  AS house_type2_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 14))  AS house_type2_area\n" +
            "\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 15)  AS house_type3_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 15)  AS house_type3_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 15))  AS house_type3_area\n" +
            "\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 28)  AS house_type4_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 28)  AS house_type4_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 28))  AS house_type4_area\n" +
            "\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 29)  AS house_type5_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 29)  AS house_type5_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 29))  AS house_type5_area\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 16)  AS house_type6_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 16)  AS house_type6_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 8) AND fclx = 16))  AS house_type6_area\n" +
            "FROM dict_detail WHERE dict_id = 6\n" +
            "\n" +
            "UNION ALL\n" +
            "\n" +
            "SELECT '纳溪区'AS area\n" +
            ",label\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 13)  AS house_type1_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 13)  AS house_type1_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 13))  AS house_type1_area\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 14)  AS house_type2_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 14)  AS house_type2_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 14))  AS house_type2_area\n" +
            "\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 15)  AS house_type3_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 15)  AS house_type3_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 15))  AS house_type3_area\n" +
            "\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 28)  AS house_type4_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 28)  AS house_type4_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 28))  AS house_type4_area\n" +
            "\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 29)  AS house_type5_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 29)  AS house_type5_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 29))  AS house_type5_area\n" +
            "\n" +
            ",(SELECT COUNT(*) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 16)  AS house_type6_tao\n" +
            ",(SELECT SUM(fcje) FROM t_shenbaoxingxi WHERE create_id IN (SELECT id FROM `user` WHERE region = 12) AND fclx = 16)  AS house_type6_money\n" +
            ",(SELECT SUM(fcmj) FROM t_house WHERE id IN (SELECT fcid FROM `t_shenbaoxingxi` WHERE create_id IN (SELECT id FROM `user` WHERE region =128) AND fclx = 16))  AS house_type6_area\n" +
            "FROM dict_detail WHERE dict_id = 6\n" +
            ") AS a  ", nativeQuery = true)
//    List<Map<String,String>> getBusinessTotalData(String startTime, String endTime, String type);
    List<BusinessTotal> getBusinessTotalData(String startTime, String endTime, String type);
}