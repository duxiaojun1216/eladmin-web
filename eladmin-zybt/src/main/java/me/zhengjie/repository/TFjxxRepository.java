package me.zhengjie.repository;

import me.zhengjie.domain.TFjxx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author zengjian
* @date 2020-03-27
*/
public interface TFjxxRepository extends JpaRepository<TFjxx, Long>, JpaSpecificationExecutor<TFjxx> {

    @Transactional
    @Modifying
    @Query(value=" update t_fjxx SET sbxxid=?1 WHERE id =?2" ,nativeQuery = true)
    int updateSbxxIdById(String sbxxid,String idsStr);

    @Transactional
    @Modifying
    @Query(value=" SELECT * FROM `t_fjxx` WHERE sbxxid=?1" ,nativeQuery = true)
    List<TFjxx> findFjxxByShenbaoxixiId(String sbxxid);
}
