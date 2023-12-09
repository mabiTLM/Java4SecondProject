package com.nanoClone.projectSecond.careers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.careers.domain.Careers;

@Repository
public class CareersDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<Careers> mapper = new RowMapper<Careers>() {
    @Override
    public Careers mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Careers(rs.getInt("id"), rs.getInt("university"), rs.getInt("member_id"),
          rs.getString("title"), rs.getString("content"), rs.getDate("start"), rs.getDate("end"),
          rs.getString("university_name"));
    }
  };

  public List<Careers> get(int memberId) {
    return jdbcTemplate.query(
        "select *, university.content as university_name from careers join university on university.id=university where member_id = ?",
        mapper, memberId);
  }

  public List<Careers> getAll() {
    return jdbcTemplate.query(
        "select *, university.content as university_name from careers join university on university.id=university",
        mapper);
  }

  public void add(Careers careers) {
    jdbcTemplate.update(
        "insert into careers (university,member_id, title, content, start, end) values (?,?,?,?,?,?)",
        careers.getUniversity(), careers.getMemberId(), careers.getTitle(), careers.getContent(),
        careers.getStart(), careers.getEnd());
  }

  public void delete(int id) {
    jdbcTemplate.update("delete from careers where id = ?", id);
  }
}
