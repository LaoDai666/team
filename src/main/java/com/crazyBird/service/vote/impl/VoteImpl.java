package com.crazyBird.service.vote.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crazyBird.dao.vote.VoteDao;
import com.crazyBird.dao.vote.VoteDetailDao;
import com.crazyBird.dao.vote.VoteRecordDao;
import com.crazyBird.dao.vote.dataobject.VoteActionDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.crazyBird.dao.vote.dataobject.VoteActionPO;
import com.crazyBird.dao.vote.dataobject.VoteRecordDO;
import com.crazyBird.service.base.ResponseCode;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;
import com.crazyBird.service.vote.VoteService;
import com.crazyBird.utils.CollectionUtil;

@Component("VoteService")
public class VoteImpl implements VoteService {
	@Autowired
	private VoteDao voteDao;
	@Autowired
	private VoteDetailDao voteDetailDao;
	@Autowired
	private VoteRecordDao voteRecordDao;

	@Override
	public ResponsePageQueryDO<List<VoteActionDO>> getVoteActionList(VoteActionPO po) {
		ResponsePageQueryDO<List<VoteActionDO>> response = new ResponsePageQueryDO<>();
		response.setPageIndex(po.getPageIndex());
		response.setPageSize(po.getPageSize());
		response.setTotal(voteDao.getVoteActionCount(po.getStatus()));
		if (response.getTotal() > 0 && response.getTotalPage() > po.getPageIndex()) {
			List<VoteActionDO> actionList = voteDao.getVoteActionlist(po);
			response.setDataResult(actionList);
			response.setCode(ResponseCode.SUCCESS);
		} else {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("暂无该类活动");
		}
		return response;
	}
	/*
	 * @Override public List<VoteActionDO> getVoteActionList(Integer status) { //
	 * TODO Auto-generated method stub return voteDao.getVoteActionlist(status); }
	 */

	@Override
	public List<VoteActionDetailDO> getActionDetailList(Long id) {
		List<VoteActionDetailDO> detailList = voteDetailDao.getVoteActionDetail(id);
		voteDao.updateVoteActionNum(id);
		return detailList;
	}

	@Override
	public VoteActionDO getAction(Long id) {
		return voteDao.getVoteAction(id);
	}

	@Override
	public List<VoteActionDO> getVoteActionHotList() {
		return voteDao.getVoteActionHotList();
	}

	@Override
	public Integer checkVoteRecord(VoteRecordDO recordDO) {
		// TODO Auto-generated method stub
		return voteRecordDao.checkVoteRecord(recordDO);
	}

	@Override
	public ResponseDO createVoteRecord(VoteRecordDO recordDO) {
		ResponseDO response = new ResponseDO<>();
		List<Long> ids = new ArrayList<>();

		if (recordDO.getDetail().indexOf(",") != -1) {
			String[] tags = recordDO.getDetail().split(",");
			for (String tag : tags) {
				if (StringUtils.isNotBlank(tag)) {
					ids.add(Long.parseLong(tag));
				}
			}
		}
		if (recordDO.getDetail().indexOf(",") == -1) {
			ids.add(Long.parseLong(recordDO.getDetail()));
		}
		if (ids.size() != recordDO.getSum() || CollectionUtil.isEmpty(ids)) {
			response.setCode(ResponseCode.ERROR);
			response.setMessage("投票异常");
		} else {
			voteDetailDao.BatchupdateVoteActionDetail(ids,recordDO.getActionId());
			voteRecordDao.insertVoteRecord(recordDO);
			voteDao.updateVoteActionSum(recordDO);
			response.setMessage("投票成功");
		}
		return response;
	}

	@Override
	public VoteActionDetailDO selectActionDetailByName(VoteActionDetailSearchDO searchDO) {
		VoteActionDetailDO detailDO = voteDetailDao.selectActionDetailByName(searchDO);
		// TODO Auto-generated method stub
		return detailDO;
	}

}
