package com.nanoClone.projectSecond.collaborations.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.collaborations.domain.Collaborations;

@Repository
public class CollaborationsDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<Collaborations> mapper = new RowMapper<Collaborations>() {
    @Override
    public Collaborations mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Collaborations(rs.getInt("id"), rs.getString("category"), rs.getString("name"),
          rs.getString("university"));
    }
  };

  public void add(Collaborations collaborations) {
    jdbcTemplate.update("insert into collaborations (category, name, university) values (?,?,?)",
        collaborations.getCategory(), collaborations.getName(), collaborations.getUniversity());
  }

  public List<Collaborations> getAllCategory(String category) {
    return jdbcTemplate.query("select * from collaborations where category = ?", mapper, category);
  }
}
