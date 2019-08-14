package com.crazyBird.dao.vote;

import com.crazyBird.dao.vote.dataobject.VoteRecordDO;

public interface VoteRecordDao {
	//将投过票的人添加到投票记录表中
		int insertVoteRecord(VoteRecordDO recordDO);
		
		//检测是否投过票
		Integer checkVoteRecord(VoteRecordDO recordDO);
}
