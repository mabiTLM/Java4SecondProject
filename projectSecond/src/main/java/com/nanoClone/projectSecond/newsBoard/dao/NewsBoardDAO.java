package com.nanoClone.projectSecond.newsBoard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.newsBoard.domain.NewsBoard;

@Repository
public class NewsBoardDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<NewsBoard> mapper = new RowMapper<NewsBoard>() {
    @Override
    public NewsBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new NewsBoard(rs.getInt("id"), rs.getString("category"), rs.getString("title"),
          rs.getString("content"), rs.getTimestamp("created_at"), rs.getInt("views"));
    }
  };

  public void add(NewsBoard newsBoard) {
    jdbcTemplate.update("insert into news_board (category, title, content) values (?,?,?)",
        newsBoard.getCategory(), newsBoard.getTitle(), newsBoard.getContent());
  }

  public int getCount() {
    return jdbcTemplate.queryForObject("select count(*) from news_board", Integer.class);
  }

  public List<NewsBoard> getAll(int idx, int count) {
    return jdbcTemplate.query("select * from news_board order by id desc limit ?, ?", mapper, idx,
        count);
  }

}
