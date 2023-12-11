package com.nanoClone.projectSecond.researchTopics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.researchTopics.domain.ResearchTopics;

@Repository
public class ResearchTopicsDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<ResearchTopics> mapper = new RowMapper<ResearchTopics>() {
    @Override
    public ResearchTopics mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new ResearchTopics(rs.getInt("id"), rs.getString("category"), rs.getString("title"),
          rs.getString("content"));
    }
  };

  public void add(ResearchTopics researchTopics) {
    jdbcTemplate.update("insert into research_topics (category, title, content) values (?,?,?)",
        researchTopics.getCategory(), researchTopics.getTitle(), researchTopics.getContent());
  }

  public List<ResearchTopics> getAll(String category) {
    return jdbcTemplate.query("select * from research_topics where category = ?", mapper, category);
  }
}
