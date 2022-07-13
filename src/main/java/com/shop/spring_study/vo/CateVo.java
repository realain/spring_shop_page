package com.shop.spring_study.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class CateVo {
	@Id
	@Column(name = "cate_num")
	private Integer cateNum;
	private String name;
	private Integer sub;
	public Integer getCateNum() {
		return cateNum;
	}
	public void setCateNum(Integer cateNum) {
		this.cateNum = cateNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSub() {
		return sub;
	}
	public void setSub(Integer sub) {
		this.sub = sub;
	}
	
	
}
