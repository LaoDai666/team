package com.crazyBird.controller.vote.model;

import java.util.List;
import com.crazyBird.controller.base.AbstractFlagModel;


public class VoteActionDetailListModel extends AbstractFlagModel{
	private Long id;
	private String actionName;
	private String actionIntro;
	private String voteRuler;
	private String actionImage;
	private String host;
	private String telephone;
	private Integer status;
	private Long visitNum;
	private Long voteSum;

	private String gmtCreated;
	private String gmtModified;
	private List<VoteActionDetailItem> voteDetailList;
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

	public List<VoteActionDetailItem> getVoteDetailList() {
		return voteDetailList;
	}
	public void setVoteDetailList(List<VoteActionDetailItem> voteDetailList) {
		this.voteDetailList = voteDetailList;
	}
}
