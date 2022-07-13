package com.shop.spring_study.vo;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/*
item_num      | int(11)
name          | varchar(255)
title         | varchar(255)
count         | int(11)
price         | int(11)
color         | varchar(255)
main_img      | varchar(255)
detail_img    | varchar(255)
model_num     | varchar(255)
member_id     | varchar(255) (Member.member_id)
post_time     | datetime
sale_time     | datetime
sale_end_time | datetime
state         | tinyint(4)
cate_num      | int(11) (Category.cate_num)
*/
@Entity
@Table(name = "item")
public class ItemVo {
	@Id
	@Column(name = "item_num")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemNum;
	private String name;
	private String title;
	private int count;
	private int price;
	private String color;
	@Column(name = "main_img")
	private String mainImg;
	@Column(name = "detail_img")
	private String detailImg;
	@Column(name = "model_num")
	private String modelNum;
	@Column(name = "member_id")
	private String memberId;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "post_time",insertable = false, updatable = false)
	private Date postTime;
	//'2022-05-19T14:23'
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "sale_time",insertable = false, updatable = false)
	private Date saleTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "sale_end_time")
	private Date saleEndTime;
	private int state;
	@Column(name = "cate_num")
	private int cateNum;
	
	//FetchType.LAZY: 1.view에서 사용할 때만 서브쿼리로 조인, 2.@Query 에서 해당 엔티티를 사용할 때
	@OneToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name="member_id",insertable = false,updatable = false)
	private MemberVo member;
	
	@OneToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name="cate_num", insertable = false, updatable=false)
	private CateVo cate;
	
	//리스트가 오니까 리스트를 불러야함
	@OneToMany
	@JoinColumn(name = "item_num", insertable = false, updatable = false)
	private List<ItemCommentVo> itemComment;

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMainImg() {
		return mainImg;
	}

	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}

	public String getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(String detailImg) {
		this.detailImg = detailImg;
	}

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public Date getSaleEndTime() {
		return saleEndTime;
	}

	public void setSaleEndTime(Date saleEndTime) {
		this.saleEndTime = saleEndTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCateNum() {
		return cateNum;
	}

	public void setCateNum(int cateNum) {
		this.cateNum = cateNum;
	}

	public MemberVo getMember() {
		return member;
	}

	public void setMember(MemberVo member) {
		this.member = member;
	}

	public CateVo getCate() {
		return cate;
	}

	public void setCate(CateVo cate) {
		this.cate = cate;
	}

	public List<ItemCommentVo> getItemComment() {
		return itemComment;
	}

	public void setItemComment(List<ItemCommentVo> itemComment) {
		this.itemComment = itemComment;
	}

	@Override
	public String toString() {
		return "ItemVo [itemNum=" + itemNum + ", name=" + name + ", title=" + title + ", count=" + count + ", price="
				+ price + ", color=" + color + ", mainImg=" + mainImg + ", detailImg=" + detailImg + ", modelNum="
				+ modelNum + ", memberId=" + memberId + ", postTime=" + postTime + ", saleTime=" + saleTime
				+ ", saleEndTime=" + saleEndTime + ", state=" + state + ", cateNum=" + cateNum + ", member=" + member
				+ ", cate=" + cate + ", itemComment=" + itemComment + "]";
	}
	
	
}
