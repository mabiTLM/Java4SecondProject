package com.nanoClone.projectSecond.journals.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.journals.domain.Journals;

@Repository
public class JournalsDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  private RowMapper<Journals> mapper = new RowMapper<Journals>() {
    @Override
    public Journals mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Journals(rs.getInt("id"), rs.getInt("selected"), rs.getDate("make_date"),
          rs.getString("title"), rs.getString("maker"), rs.getString("book_name"),
          rs.getString("volume"), rs.getString("page"), rs.getString("impact_factor"),
          rs.getString("image"), rs.getString("file"), rs.getString("link"));
    }
  };

  public void add(Journals journals) {
    jdbcTemplate.update(
        "insert into journals (selected, make_date, title, maker, book_name, volume, page, impact_factor, image, file, link) values (?,?,?,?,?,?,?,?,?,?,?)",
        journals.getSelected(), journals.getMakeDate(), journals.getTitle(), journals.getMaker(),
        journals.getBookName(), journals.getVolume(), journals.getPage(),
        journals.getImpactFactor(), journals.getImage(), journals.getFile(), journals.getLink());
  }

  public List<Journals> getAll() {
    return jdbcTemplate.query("select * from journals order by make_date", mapper);
  }

  public List<Journals> getAll(int idx, int count) {
    return jdbcTemplate.query("select * from journals order by id desc limit ?, ?", mapper, idx,
        count);
  }

  public List<Journals> getIsSelect(int selected) {
    return jdbcTemplate.query("select * from journals where selected = ? order by make_date",
        mapper, selected);
  }

}
