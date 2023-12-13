package com.nanoClone.projectSecond.links.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.links.domain.Links;

@Repository
public class LinksDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<Links> mapper = new RowMapper<Links>() {
    @Override
    public Links mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Links(rs.getInt("id"), rs.getString("category"), rs.getString("image"),
          rs.getString("link"));
    }
  };

  public void add(Links links) {
    jdbcTemplate.update("insert into links (category, image, link) values (?,?,?)",
        links.getCategory(), links.getImage(), links.getLink());
  }

  public List<Links> getLinks() {
    return jdbcTemplate.query("select * from links where category = 'links'", mapper);
  }

  public List<Links> getFunding() {
    return jdbcTemplate.query("select * from links where category = 'funding_sources'", mapper);
  }
}
