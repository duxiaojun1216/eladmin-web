package me.zhengjie.repository;

import me.zhengjie.domain.TBzwh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author dengjie
* @date 2020-03-26
*/
public interface TBzwhRepository extends JpaRepository<TBzwh, Integer>, JpaSpecificationExecutor<TBzwh> {
}