package com.nanoClone.projectSecond.futureProspective.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.futureProspective.domain.FutureProspective;

@Repository
public class FutureProspectiveDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<FutureProspective> mapper = new RowMapper<FutureProspective>() {
    @Override
    public FutureProspective mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new FutureProspective(rs.getInt("id"), rs.getString("content"));
    }
  };

  public void add(FutureProspective futureProspective) {
    jdbcTemplate.update("insert into future_prospective (content) values (?)",
        futureProspective.getContent());
  }

  public void update(FutureProspective futureProspective) {
    jdbcTemplate.update("update future_prospective set content = ? where id = ?",
        futureProspective.getContent(), futureProspective.getId());
  }

  public FutureProspective get() {
    return jdbcTemplate.queryForObject("select * from future_prospective", mapper);
  }
}
