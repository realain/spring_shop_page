package com.shop.spring_study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.shop.spring_study.vo.ItemBasketVo;


public interface ItemBasketRepository extends JpaRepository<ItemBasketVo, Integer> {
	
	public Iterable<ItemBasketVo> findByMemberId(String memberId); 
	
	public ItemBasketVo findByItemNumAndMemberId(int itemNum,String MemberId);
}
