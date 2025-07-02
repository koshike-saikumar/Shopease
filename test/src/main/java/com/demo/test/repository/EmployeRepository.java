package com.demo.test.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.test.entity.MyEntity;

@Repository
public interface EmployeRepository extends JpaRepository<MyEntity, Long> {

	@Query(nativeQuery = true, value = """
select js.*,count(ja.*) application_no from jobs js
			left join job_applications ja on js.id=ja.job_id
			where employe_id=:employe_id group by js.id	order by application_no desc		""")
	List<Map<String, Object>> jobs(@Param("employe_id") Integer employeId);

	@Query(nativeQuery = true, value = """
			select * from jobs where employe_id=:employe_id and id=:id
			""")
	List<Map<String, Object>> job(@Param("employe_id") Integer employeId, @Param("id") Integer id);

	@Query(nativeQuery = true, value = """
						select jb.*,json_agg(ja.*) job_applications from jobs jb
			left join job_applications ja on jb.id=ja.job_id
			where jb.id=:id group by jb.id
						""")
	List<Map<String, Object>> jobApplication(@Param("id") Integer id);

}
