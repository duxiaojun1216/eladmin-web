package me.zhengjie.repository;

import me.zhengjie.domain.TShenbaoxingxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author zengjian
* @date 2020-03-25
*/
public interface TShenbaoxingxiRepository extends JpaRepository<TShenbaoxingxi, Long>, JpaSpecificationExecutor<TShenbaoxingxi> {
}