package com.crazyBird.controller.vote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazyBird.controller.base.SimpleFlagModel;
import com.crazyBird.controller.vote.model.VoteActionDetailItem;
import com.crazyBird.controller.vote.model.VoteActionDetailListModel;
import com.crazyBird.controller.vote.model.VoteActionDetailModel;
import com.crazyBird.controller.vote.model.VoteActionHotListModel;
import com.crazyBird.controller.vote.model.VoteActionItem;
import com.crazyBird.controller.vote.model.VoteActionListModel;
import com.crazyBird.controller.vote.param.VoteActionDetailListParam;
import com.crazyBird.controller.vote.param.VoteActionDetailParam;
import com.crazyBird.controller.vote.param.VoteActionParam;
import com.crazyBird.controller.vote.param.VoteActionSearchDetailParam;
import com.crazyBird.dao.vote.dataobject.VoteActionDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.crazyBird.dao.vote.dataobject.VoteActionPO;
import com.crazyBird.dao.vote.dataobject.VoteRecordDO;
import com.crazyBird.model.enums.HttpCodeEnum;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.vote.VoteService;
import com.crazyBird.utils.CollectionUtil;
import com.crazyBird.utils.DateUtil;
import com.crazyBird.utils.PageUtils;

@Component
public class VoteProcess {
	@Autowired
	private VoteService voteService;

	public VoteActionListModel getActionList(VoteActionParam param) {
		VoteActionListModel model = new VoteActionListModel();

		if (param.getStatus() == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("没有此状态活动");
			return model;
		}

		PageUtils.resetPageParam(param);
		// List<VoteActionItem> actionItems = new ArrayList<>();
		VoteActionPO po = new VoteActionPO();
		po.setStatus(param.getStatus());
		po.setPageIndex(param.getPageNo() - 1);
		po.setPageSize(param.getPageSize());
		ResponsePageQueryDO<List<VoteActionDO>> response = voteService.getVoteActionList(po);
		if (response.isSuccess()) {
			PageUtils.setPageModel(model, param, response.getTotal());
			model.setVoteList(convertVoteAction(response.getDataResult()));
		} else {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
		}
		/*
		 * if (CollectionUtil.isNotEmpty(actionList)) { for (VoteActionDO voteActionDO :
		 * actionList) { VoteActionItem actionItem = new VoteActionItem();
		 * actionItem.setId(voteActionDO.getId());
		 * actionItem.setActionIntro(voteActionDO.getActionIntro());
		 * actionItem.setActionName(voteActionDO.getActionName());
		 * actionItem.setGmtCreated(voteActionDO.getGmtCreated());
		 * actionItem.setGmtModified(voteActionDO.getGmtModified());
		 * actionItem.setHost(voteActionDO.getHost());
		 * actionItem.setStatus(voteActionDO.getStatus());
		 * actionItem.setTelephone(voteActionDO.getTelephone());
		 * actionItem.setVisitNum(voteActionDO.getVisitNum());
		 * actionItem.setVoteRuler(voteActionDO.getVoteRuler());
		 * actionItem.setVoteSum(voteActionDO.getVoteSum());
		 * actionItems.add(actionItem); } }
		 * 
		 * model.setVoteList(actionItems);
		 */
		return model;
	}

	public VoteActionDetailListModel getActionDetailList(Long id) {
		VoteActionDetailListModel model = new VoteActionDetailListModel();
		if (id == null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数为空");
		}
		VoteActionDO voteActionDO = voteService.getAction(id);
		List<VoteActionDetailItem> detailItems = new ArrayList<>();
		List<VoteActionDetailDO> tags = voteService.getActionDetailList(id);
		if (CollectionUtil.isEmpty(tags)) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("活动出错");
			return model;
		}
		for (VoteActionDetailDO tag : tags) {
			if (tag != null) {
				VoteActionDetailItem item = new VoteActionDetailItem();
				item.setActionId(tag.getActionId());
				item.setBranch(tag.getBranch());
				item.setClassName(tag.getClassName());
				item.setCompete(tag.getCompete());
				item.setContent(tag.getContent());
				item.setHonor(tag.getHonor());
				item.setId(tag.getId());
				item.setImageUrl(tag.getImageUrl());
				item.setNum(tag.getNum());
				item.setPeopleName(tag.getPolitical());
				item.setPolitical(tag.getPolitical());
				item.setPost(tag.getPost());
				item.setRecommend(tag.getRecommend());
				item.setStory(tag.getStory());
				detailItems.add(item);
			}
		}

		model.setActionIntro(voteActionDO.getActionIntro());
		model.setActionName(voteActionDO.getActionName());
		model.setGmtCreated(DateUtil.formatDate(voteActionDO.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
		model.setGmtModified(DateUtil.formatDate(voteActionDO.getGmtModified(), DateUtil.DATE_FORMAT_YMDHMS));
		model.setHost(voteActionDO.getHost());
		model.setActionImage(voteActionDO.getActionImage());
		model.setId(voteActionDO.getId());
		model.setStatus(voteActionDO.getStatus());
		model.setTelephone(voteActionDO.getTelephone());
		model.setVisitNum(voteActionDO.getVisitNum());
		model.setVoteRuler(voteActionDO.getVoteRuler());
		model.setVoteSum(voteActionDO.getVoteSum());
		model.setVoteDetailList(detailItems);
		return model;
	}

	public VoteActionHotListModel getVoteActionHotList() {
		VoteActionHotListModel model = new VoteActionHotListModel();
		List<VoteActionDO> actionList = voteService.getVoteActionHotList();
		if (CollectionUtil.isEmpty(actionList)) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("暂无进行中的热门活动");
			return model;
		}
		List<VoteActionItem> actionItems = convertVoteAction(actionList);
		model.setVoteList(actionItems);
		return model;
	}

	public SimpleFlagModel createVoteDetailNum(VoteActionDetailListParam param) {
		SimpleFlagModel model = new SimpleFlagModel();
		VoteRecordDO recordDO = new VoteRecordDO();
		recordDO.setActionId(param.getActionId());
		recordDO.setStudentId(param.getStudentId());
		recordDO.setDetail(param.getDetail());
		recordDO.setSum(param.getSum());
		if(voteService.checkVoteRecord(recordDO)!=null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("你已经投过票");
			return model;
		}
	/*	String[] ids=param.getDetail().split(",");
		if(ids.length > param.getSum()||ids.length == 0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("票数异常");
			return model;
		}*/
		
		ResponseDO response = voteService.createVoteRecord(recordDO);
		if(!response.isSuccess()){
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage(response.getMessage());
			
		}
		model.setMessage(response.getMessage());
		return model;
	}
	
	public VoteActionDetailModel selectActionDetailByName(VoteActionSearchDetailParam param) {
		VoteActionDetailModel model= new VoteActionDetailModel();
		if(StringUtils.isBlank(param.getPeopleName())||param.getActionId()==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("参数错误");
		}
		VoteActionDetailSearchDO searchDO = new VoteActionDetailSearchDO();
		
		searchDO.setActionId(param.getActionId());
		searchDO.setPeopleName(param.getPeopleName());
		
		VoteActionDetailDO detail = voteService.selectActionDetailByName(searchDO);
		if(detail==null) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("找不到结果");
		}
		model.setActionId(detail.getActionId());
		model.setBranch(detail.getBranch());
		model.setClassName(detail.getClassName());
		model.setCompete(detail.getCompete());
		model.setContent(detail.getContent());
		model.setHonor(detail.getHonor());
		model.setId(detail.getId());
		model.setImageUrl(detail.getImageUrl());
		model.setNum(detail.getNum());
		model.setPeopleName(detail.getPolitical());
		model.setPolitical(detail.getPolitical());
		model.setPost(detail.getPost());
		model.setRecommend(detail.getRecommend());
		model.setStory(detail.getStory());
		
		return model;	
	}

	private List<VoteActionItem> convertVoteAction(List<VoteActionDO> tags) {
		List<VoteActionItem> actionItems = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(tags)) {
			for (VoteActionDO tag : tags) {
				if(tag!=null) {
				VoteActionItem item = new VoteActionItem();
				item.setId(tag.getId());
				item.setActionIntro(tag.getActionIntro());
				item.setActionName(tag.getActionName());
				item.setGmtCreated(DateUtil.formatDate(tag.getGmtCreated(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setGmtModified(DateUtil.formatDate(tag.getGmtModified(), DateUtil.DATE_FORMAT_YMDHMS));
				item.setActionImage(tag.getActionImage());
				item.setHost(tag.getHost());
				item.setStatus(tag.getStatus());
				item.setTelephone(tag.getTelephone());
				item.setVisitNum(tag.getVisitNum());
				item.setVoteRuler(tag.getVoteRuler());
				item.setVoteSum(tag.getVoteSum());
				actionItems.add(item);
				}
			}
		}
		return actionItems;
	}

}
