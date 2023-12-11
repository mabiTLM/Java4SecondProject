package com.nanoClone.projectSecond.groupMissonStatement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.groupMissonStatement.dao.GroupMissionStatementDAO;
import com.nanoClone.projectSecond.groupMissonStatement.domain.GroupMissionStatement;

@Service
public class GroupMissionStatementService {
  @Autowired
  GroupMissionStatementDAO groupMissionStatementDAO;

  public void add(GroupMissionStatement groupMissionStatement) {
    try {
      if (groupMissionStatementDAO.get() != null) {
        groupMissionStatementDAO.update(groupMissionStatement);
      }
    } catch (Exception e) {
      groupMissionStatementDAO.add(groupMissionStatement);
      e.printStackTrace();
    }
  }

  public GroupMissionStatement get() {
    return groupMissionStatementDAO.get();
  }
}
