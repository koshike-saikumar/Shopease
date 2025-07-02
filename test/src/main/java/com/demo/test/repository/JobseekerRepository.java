package com.demo.test.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.test.entity.UserEntity;

@Repository
public interface JobseekerRepository extends JpaRepository<UserEntity, Integer> {

	@Query(nativeQuery = true, value = """
			select js.*,case when ja.job_id is not null then true else false end job_status  from jobs js
						left join job_applications ja on js.id=ja.job_id where js.employe_id=:employe_id""")
	List<Map<String, Object>> searchJobs(@Param("employe_id") Integer id);

	@Query(nativeQuery = true, value = """
						select * from job_applications ja
			left join jobs jb on ja.job_id=jb.id
			where job_seeker_id=:job_seeker_id
						""")
	List<Map<String, Object>> appliedJobs(@Param("job_seeker_id") Integer jobSeekerId);

	@Query(nativeQuery = true, value = """
									select js.*,case when ja.job_id is not null then false else true end job_status from jobs js
			left join (select * from job_applications  where job_seeker_id =:job_seeker_id) ja on js.id=ja.job_id
			where skills ilike %:skills% order by ja.id desc 
									""")
	List<Map<String, Object>> basedOnSkills(@Param("skills") String skills,@Param("job_seeker_id") Integer jobSeekerId);

}
