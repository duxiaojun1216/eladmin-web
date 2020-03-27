package me.zhengjie.repository;

import me.zhengjie.domain.THouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author zengjian
* @date 2020-03-27
*/
public interface THouseRepository extends JpaRepository<THouse, Long>, JpaSpecificationExecutor<THouse> {
}