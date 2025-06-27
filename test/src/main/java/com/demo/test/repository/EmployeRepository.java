package com.demo.test.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.test.entity.MyEntity;

@Repository
public interface EmployeRepository extends JpaRepository<MyEntity, Long>{
	
	@Query(nativeQuery = true, value = """
			select * from jobs where employe_id=:employe_id
			""")
	List<Map<String, Object>> jobs(@Param("employe_id") Integer employeId);

}
