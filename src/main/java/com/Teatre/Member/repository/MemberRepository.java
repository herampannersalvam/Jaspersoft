package com.Teatre.Member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Teatre.Member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

//	@Query("SELECT m FROM Member m WHERE m.id = :key OR m.name = :key OR m.age = :key OR m.gender = :key OR m.location = :key")
	/*
	 * @Query(value =
	 * "SELECT * FROM member  WHERE id = :key OR name = :key  OR age = :key OR gender = :key OR location = :key"
	 * ,nativeQuery = true) List<Object> getkey(@Param("key")Object key);
	 * 
	 */
	@Query(value = "select * from Member m where m.id =:key or m.name=:key or m.age=:key or m.location =:key or m.gender =:key",nativeQuery = true)
	List<Member> findBykey(Object key);

	@Query(value = "select * from Member m where m.id =:key or m.name=:key or m.age=:key or m.location =:key or m.gender =:key",nativeQuery = true)
	List<Member> getfilter(Object key);

}
