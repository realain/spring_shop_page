package com.shop.spring_study.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shop.spring_study.vo.MemberVo;

public interface MemberRepository extends CrudRepository<MemberVo, String>{
	public List<MemberVo> findAllByOrderByNameDesc(); 
	public List<MemberVo> findAllByOrderBySignupTimeAsc();
	public List<MemberVo> findAllByOrderBySignupTimeDesc();

	public MemberVo findByEmail(String email);
	public Optional<MemberVo> findByPhone(String phone);
	public MemberVo findByIdAndPw(String id,String pw); 
	@Query(value = "SELECT mem FROM MemberVo mem WHERE mem.email=:email")
	public Optional<MemberVo> selectJPQLByEmail(@Param(value="email") String email);
	@Query(value = "SELECT * FROM member WHERE email=?1",nativeQuery = true)
	public Optional<MemberVo> selectByEmail(String email);
	
	public Page<MemberVo> findAll(Pageable pageable);
}
