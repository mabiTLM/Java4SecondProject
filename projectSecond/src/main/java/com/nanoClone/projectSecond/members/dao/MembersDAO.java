package com.nanoClone.projectSecond.members.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.members.domain.Members;

@Repository
public class MembersDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<Members> mapper = new RowMapper<Members>() {
    @Override
    public Members mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Members(rs.getInt("id"), rs.getString("email"), rs.getString("user_id"),
          rs.getString("password"), rs.getString("category"), rs.getString("english_name"),
          rs.getString("name"), rs.getString("position"), rs.getString("info"),
          rs.getString("image"));
    }
  };

  public void add(Members members) {
    jdbcTemplate.update(
        "insert into members (email, user_id, password, name, english_name, position) values (?,?,?,?,?,?)",
        members.getEmail(), members.getEmail(), members.getPassword(), members.getName(),
        members.getEnglishName(), members.getPosition());
  }

  public Members get(int id) {
    return jdbcTemplate.queryForObject("select * from members where id = ?", mapper, id);
  }

  public Members get(String userId) {
    return jdbcTemplate.queryForObject("select * from members where user_id = ?", mapper, userId);
  }
}
