package com.nanoClone.projectSecond.honorsAndAwards.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.honorsAndAwards.domain.HonorsAndAwards;

@Repository
public class HonorsAndAwardsDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<HonorsAndAwards> mapper = new RowMapper<HonorsAndAwards>() {
    @Override
    public HonorsAndAwards mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new HonorsAndAwards(rs.getInt("id"), rs.getInt("member_id"), rs.getString("title"),
          rs.getDate("award_date"), rs.getString("contents"));
    }
  };

  public void add(HonorsAndAwards honorsAndAwards) {
    jdbcTemplate.update(
        "insert into honors_and_awards (member_id,title,award_date,contents) values (?,?,?,?)",
        honorsAndAwards.getMemberId(), honorsAndAwards.getTitle(), honorsAndAwards.getAwardDate(),
        honorsAndAwards.getContents());
  }

  public List<HonorsAndAwards> get(int memberId) {
    return jdbcTemplate.query("select * from honors_and_awards where member_id = ?", mapper,
        memberId);
  }
}
