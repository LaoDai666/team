package com.crazyBird.dao.vote.dataobject;

import java.sql.Date;

public class VoteActionClickDO {
	private String actionName;
	private String actionIntro;
	private String voteRuler;
	private String host;
	private String actionImage;
	private String telephone;
	private Integer status;
	private Long visitNum;
	private Long voteSum;
	private Date gmtCreated;
	private Date gmtModified;
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
	public String getActionImage() {
		return actionImage;
	}
	public void setActionImage(String actionImage) {
		this.actionImage = actionImage;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Date getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	
}
