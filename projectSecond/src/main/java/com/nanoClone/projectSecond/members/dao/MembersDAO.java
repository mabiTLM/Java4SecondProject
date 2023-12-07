package com.nanoClone.projectSecond.members.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.members.domain.Members;

@Repository
public class MembersDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<Members> mapper = new RowMapper<Members>() {
    @Override
    public Members mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Members(rs.getInt("id"), rs.getString("email"), rs.getString("user_id"),
          rs.getString("password"), rs.getString("category"), rs.getString("english_name"),
          rs.getString("name"), rs.getString("position"), rs.getString("info"),
          rs.getString("image"), rs.getInt("is_manager"), rs.getString("chinese_name"),
          rs.getString("address"), rs.getString("phone"), rs.getString("site"),
          rs.getString("link"));
    }
  };

  public void add(Members members) {
    jdbcTemplate.update(
        "insert into members (email, user_id, password, name, english_name, position) values (?,?,?,?,?,?)",
        members.getEmail(), members.getEmail(), members.getPassword(), members.getName(),
        members.getEnglishName(), members.getPosition());
  }

  public Members get(int id) {
    return jdbcTemplate.queryForObject("select * from members where id = ?", mapper, id);
  }

  public Members get(String userId) {
    return jdbcTemplate.queryForObject("select * from members where user_id = ?", mapper, userId);
  }

  public List<Members> getAll(int idx, int count) {
    return jdbcTemplate.query("select * from members order by id desc limit ?, ?", mapper, idx,
        count);
  }

  public Members getLastProfessor() {
    return jdbcTemplate.queryForObject(
        "select * from members where category = 'professor' order by id limit 0, 1", mapper);
  }

  public List<Members> getAllProfessor() {
    return jdbcTemplate.query("select * from members where category='professor' order by id",
        mapper);
  }

  public int getCount() {
    return jdbcTemplate.queryForObject("select count(*) from members", Integer.class);
  }

  public void update(Members members) {
    jdbcTemplate.update(
        "update members set email = ?, user_id = ?, password = ?, category = ?, name = ?, english_name = ?, position = ?, info = ?, image = ?, is_manager = ?, chinese_name = ?, address = ?, phone = ?, site = ?, link = ? where id = ?",
        members.getEmail(), members.getUserId(), members.getPassword(), members.getCategory(),
        members.getName(), members.getEnglishName(), members.getPosition(), members.getInfo(),
        members.getImage(), members.getIsManager(), members.getChineseName(), members.getAddress(),
        members.getPhone(), members.getSite(), members.getLink(), members.getId());
  }
}
