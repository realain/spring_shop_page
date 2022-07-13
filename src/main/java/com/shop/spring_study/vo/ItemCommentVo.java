package com.shop.spring_study.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item_comment")
public class ItemCommentVo {
	@Id
	@Column(name = "comment_num")
	private int commentNum;
	private String title;
	private String contents;
	private String img;
	@Column(name = "post_time")
	private Date postTime;
	@Column(name = "delivery_grade")
	private byte deliveryGrade;
	@Column(name = "item_grade")
	private byte itemGrade;
	@Column(name = "sellerGrade")
	private byte seller_grade;
	@Column(name = "item_num")
	private int itemNum;
	@Column(name = "memberId")
	private String member_id;
	private byte state;
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public byte getDeliveryGrade() {
		return deliveryGrade;
	}
	public void setDeliveryGrade(byte deliveryGrade) {
		this.deliveryGrade = deliveryGrade;
	}
	public byte getItemGrade() {
		return itemGrade;
	}
	public void setItemGrade(byte itemGrade) {
		this.itemGrade = itemGrade;
	}
	public byte getSeller_grade() {
		return seller_grade;
	}
	public void setSeller_grade(byte seller_grade) {
		this.seller_grade = seller_grade;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public byte getState() {
		return state;
	}
	public void setState(byte state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ItemCommentVo [commentNum=" + commentNum + ", title=" + title + ", contents=" + contents + ", img="
				+ img + ", postTime=" + postTime + ", deliveryGrade=" + deliveryGrade + ", itemGrade=" + itemGrade
				+ ", seller_grade=" + seller_grade + ", itemNum=" + itemNum + ", member_id=" + member_id + ", state="
				+ state + "]";
	}
	
}
