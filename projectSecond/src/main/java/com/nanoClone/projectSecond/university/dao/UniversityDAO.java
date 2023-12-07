package com.nanoClone.projectSecond.university.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.university.domain.University;

@Repository
public class UniversityDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<University> mapper = new RowMapper<University>() {
    @Override
    public University mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new University(rs.getInt("id"), rs.getString("content"));
    }
  };

  public University get(int id) {
    return jdbcTemplate.queryForObject("select * from university where id = ?", mapper, id);
  }

  public List<University> getAll() {
    return jdbcTemplate.query("select * from university order by id", mapper);
  }
}
