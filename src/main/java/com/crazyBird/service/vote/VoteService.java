package com.crazyBird.service.vote;

import java.util.List;


import com.crazyBird.dao.vote.dataobject.VoteActionDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailDO;
import com.crazyBird.dao.vote.dataobject.VoteActionDetailSearchDO;
import com.crazyBird.dao.vote.dataobject.VoteActionPO;
import com.crazyBird.dao.vote.dataobject.VoteRecordDO;
import com.crazyBird.service.base.ResponseDO;
import com.crazyBird.service.base.ResponsePageQueryDO;

public interface VoteService {
	public ResponsePageQueryDO<List<VoteActionDO>> getVoteActionList(VoteActionPO po);
	public List<VoteActionDetailDO> getActionDetailList(Long id);
	public VoteActionDO getAction(Long id);
	public List<VoteActionDO> getVoteActionHotList();
	public Integer checkVoteRecord(VoteRecordDO recordDO);
	public ResponseDO createVoteRecord(VoteRecordDO recordDO);
	public VoteActionDetailDO selectActionDetailByName(VoteActionDetailSearchDO searchDO);
}
