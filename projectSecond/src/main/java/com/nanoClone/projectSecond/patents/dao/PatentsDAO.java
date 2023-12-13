package com.nanoClone.projectSecond.patents.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.patents.domain.Patents;

@Repository
public class PatentsDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<Patents> mapper = new RowMapper<Patents>() {
    @Override
    public Patents mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Patents(rs.getInt("id"), rs.getString("content"), rs.getInt("is_regist"),
          rs.getString("regist_number"), rs.getDate("regist_date"));
    }
  };

  public void add(Patents patents) {
    jdbcTemplate.update(
        "insert into patents (content, is_regist, regist_number, regist_date) values (?,?,?,?)",
        patents.getContent(), patents.getIsRegist(), patents.getRegistNumber(),
        patents.getRegistDate());
  }

  public List<Patents> getAll() {
    return jdbcTemplate.query("select * from patents order by id desc", mapper);
  }
}
