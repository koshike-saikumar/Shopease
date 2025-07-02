package com.demo.test.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.test.entity.UserEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	@Query(nativeQuery = true, value = "select count(*) from users  where email=:email")
	Integer alredyRegister(@Param("email") String email);

	@Query(nativeQuery = true, value = """
			select * from users where id=:id
			""")
	List<Map<String, Object>> userDetails(@Param("id") Integer id);

	@Query(nativeQuery = true, value = """
			select * from users where email=:email and password=:password
			""")
	List<Map<String, Object>> getUserDetails(@Param("email") String email, @Param("password") String password);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = """
			UPDATE users SET name =:name, password =:password WHERE id =:id

			""")
	Integer updateWithPassword(@Param("id") Long Id, @Param("name") String name, @Param("password") String password);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = """
			UPDATE users SET name =:name WHERE id =:id

			""")
	Integer updateWithOutPassword(@Param("id") Long Id, @Param("name") String name);

}
