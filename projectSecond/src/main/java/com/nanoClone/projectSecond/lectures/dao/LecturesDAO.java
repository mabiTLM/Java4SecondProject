package com.nanoClone.projectSecond.lectures.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.lectures.domain.Lectures;

@Repository
public class LecturesDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<Lectures> mapper = new RowMapper<Lectures>() {
    @Override
    public Lectures mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Lectures(rs.getInt("id"), rs.getString("name"), rs.getString("lecture_when"));
    }
  };

  public void add(Lectures lectures) {
    jdbcTemplate.update("insert into lectures (name, lecture_when) values (?,?)",
        lectures.getName(), lectures.getLectureWhen());
  }

  public List<Lectures> getAll() {
    return jdbcTemplate.query("select * from lectures order by id", mapper);
  }

}
