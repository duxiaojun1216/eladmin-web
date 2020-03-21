package me.zhengjie.repository;

import me.zhengjie.domain.TPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author zengjina
* @date 2020-03-21
*/
public interface TPersonnelRepository extends JpaRepository<TPersonnel, Long>, JpaSpecificationExecutor<TPersonnel> {
}