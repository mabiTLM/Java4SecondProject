package com.nanoClone.projectSecond.alumni.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.alumni.domain.Alumni;

@Repository
public class AlumniDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<Alumni> mapper = new RowMapper<Alumni>() {
    @Override
    public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Alumni(rs.getInt("id"), rs.getInt("member_id"), rs.getString("content"),
          rs.getDate("start"), rs.getDate("end"));
    }
  };

  public void add(Alumni alumni) {
    jdbcTemplate.update("insert into alumni (member_id, content, start, end) values (?,?,?,?)",
        alumni.getMemberId(), alumni.getContent(), alumni.getStart(), alumni.getEnd());
  }

  public Alumni get(int meberId) {
    return jdbcTemplate.queryForObject("select * from alumni where member_id = ?", mapper, meberId);
  }

  public List<Alumni> getAll() {
    return jdbcTemplate.query("select * from alumni", mapper);
  }

  public void update(Alumni alumni) {
    jdbcTemplate.update("update alumni set content=?, start=?, end=? where member_id = ?",
        alumni.getContent(), alumni.getStart(), alumni.getEnd(), alumni.getMemberId());
  }
}
