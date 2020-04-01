package me.zhengjie.repository;

import me.zhengjie.domain.TEntrust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author zengjian
* @date 2020-04-01
*/
public interface TEntrustRepository extends JpaRepository<TEntrust, Long>, JpaSpecificationExecutor<TEntrust> {
}