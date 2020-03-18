package test.repository;

import test.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author wangwei
* @date 2020-02-29
*/
public interface TestRepository extends JpaRepository<Test, Integer>, JpaSpecificationExecutor<Test> {
}