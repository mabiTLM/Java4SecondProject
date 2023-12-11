package com.nanoClone.projectSecond.instruments.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.instruments.domain.Instruments;

@Repository
public class InstrumentsDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<Instruments> mapper = new RowMapper<Instruments>() {
    @Override
    public Instruments mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Instruments(rs.getInt("id"), rs.getString("title"), rs.getString("image"));
    }
  };

  public void add(Instruments instruments) {
    jdbcTemplate.update("insert into instruments (title, image) values (?,?)",
        instruments.getTitle(), instruments.getImage());
  }

  public List<Instruments> getAll(int start, int count) {
    return jdbcTemplate.query("select * from instruments order by id desc limit ?, ?", mapper,
        start, count);
  }

  public int getCount() {
    return jdbcTemplate.queryForObject("select count(*) from instruments", Integer.class);
  }
}
