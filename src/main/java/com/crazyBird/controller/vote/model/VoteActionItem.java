package com.crazyBird.controller.vote.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class VoteActionItem {
	private Long id;
	private String actionName;
	private String actionImage;
	private String actionIntro;
	private String voteRuler;
	private String host;
	private String telephone;
	private Integer status;
	private Long visitNum;
	private Long voteSum;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String gmtCreated;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private String gmtModified;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActionImage() {
		return actionImage;
	}
	public void setActionImage(String actionImage) {
		this.actionImage = actionImage;
	}
	public String getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(String gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public String getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionIntro() {
		return actionIntro;
	}
	public void setActionIntro(String actionIntro) {
		this.actionIntro = actionIntro;
	}
	public String getVoteRuler() {
		return voteRuler;
	}
	public void setVoteRuler(String voteRuler) {
		this.voteRuler = voteRuler;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getVisitNum() {
		return visitNum;
	}
	public void setVisitNum(Long visitNum) {
		this.visitNum = visitNum;
	}
	public Long getVoteSum() {
		return voteSum;
	}
	public void setVoteSum(Long voteSum) {
		this.voteSum = voteSum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	
}
