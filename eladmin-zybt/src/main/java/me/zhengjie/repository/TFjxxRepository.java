package me.zhengjie.repository;

import me.zhengjie.domain.TFjxx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author zengjian
* @date 2020-03-27
*/
public interface TFjxxRepository extends JpaRepository<TFjxx, Long>, JpaSpecificationExecutor<TFjxx> {
}