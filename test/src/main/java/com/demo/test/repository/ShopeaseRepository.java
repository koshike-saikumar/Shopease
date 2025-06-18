package com.demo.test.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopeaseRepository extends JpaRepository<com.demo.test.entity.MyEntity, Long>{
	
	@Query(nativeQuery = true, value = """
			SELECT * FROM employees;
			""")
	List<Map<String, Object>> test();

}
