package com.nanoClone.projectSecond.groupMissonStatement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.groupMissonStatement.domain.GroupMissionStatement;

@Repository
public class GroupMissionStatementDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<GroupMissionStatement> mapper = new RowMapper<GroupMissionStatement>() {
    @Override
    public GroupMissionStatement mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new GroupMissionStatement(rs.getInt("id"), rs.getString("content"));
    }
  };

  public void add(GroupMissionStatement groupMissionStatement) {
    jdbcTemplate.update("insert into group_mission_statement (content) values (?)",
        groupMissionStatement.getContent());
  }

  public void update(GroupMissionStatement groupMissionStatement) {
    jdbcTemplate.update("update group_mission_statement set content = ? where id = ?",
        groupMissionStatement.getContent(), groupMissionStatement.getId());
  }

  public GroupMissionStatement get() {
    return jdbcTemplate.queryForObject("select * from group_mission_statement", mapper);
  }
}
