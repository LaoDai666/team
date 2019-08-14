package com.crazyBird.controller.user.model;

import java.util.List;
import com.crazyBird.controller.base.AbstractFlagModel;
import com.crazyBird.dao.user.dataobject.studentDO;

public class studentRecord extends AbstractFlagModel {

	private List<studentDO> VoteList;

	public List<studentDO> getVoteList() {
		return VoteList;
	}

	public void setVoteList(List<studentDO> voteList) {
		VoteList = voteList;
	}
	
}
