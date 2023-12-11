package com.nanoClone.projectSecond.publications.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.newsBoard.domain.NewsBoard;

@Repository
public class PublicationsDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  private RowMapper<NewsBoard> mapper = new RowMapper<NewsBoard>() {
    @Override
    public NewsBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new NewsBoard(rs.getInt("id"), rs.getString("category"), rs.getString("title"),
          rs.getString("content"), rs.getDate("created_at"), rs.getInt("views"));
    }
  };
}
