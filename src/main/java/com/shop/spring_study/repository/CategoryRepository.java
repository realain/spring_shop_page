package com.shop.spring_study.repository;

import org.springframework.data.repository.CrudRepository;

import com.shop.spring_study.vo.CateVo;

public interface CategoryRepository extends CrudRepository<CateVo, Integer>{

}
