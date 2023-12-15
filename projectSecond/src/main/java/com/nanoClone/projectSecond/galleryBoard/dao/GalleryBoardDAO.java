package com.nanoClone.projectSecond.galleryBoard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nanoClone.projectSecond.galleryBoard.domain.GalleryBoard;

@Repository
public class GalleryBoardDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RowMapper<GalleryBoard> mapper = new RowMapper<GalleryBoard>() {
    @Override
    public GalleryBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new GalleryBoard(rs.getInt("id"), rs.getInt("member_id"), rs.getString("title"),
          rs.getString("content"), rs.getDate("created_at"), rs.getInt("views"),
          rs.getString("member_name"));
    }
  };

  public void add(GalleryBoard galleryBoard) {
    jdbcTemplate.update("insert into gallery_board (member_id, title, content) values (?,?,?)",
        galleryBoard.getMemberId(), galleryBoard.getTitle(), galleryBoard.getContent());
  }

  public GalleryBoard get(int id) {
    return jdbcTemplate.queryForObject(
        "select *, members.name as member_name from gallery_board join members on gallery_board.member_id=members.id where gallery_board.id = ?",
        mapper, id);
  }

  public List<GalleryBoard> getAll(int idx, int count) {
    return jdbcTemplate.query(
        "select *, members.name as member_name from gallery_board join members on gallery_board.member_id=members.id order by gallery_board.id desc limit ?, ?",
        mapper, idx, count);
  }

  public List<GalleryBoard> getAll(String search, int idx, int count) {
    return jdbcTemplate.query(
        "select *, members.name as member_name from gallery_board join members on gallery_board.member_id=members.id where title like ? order by gallery_board.id desc limit ?, ?",
        mapper, "%" + search + "%", idx, count);
  }

  public int getCount() {
    return jdbcTemplate.queryForObject("select count(*) from gallery_board", Integer.class);
  }

  public int getCount(String search) {
    return jdbcTemplate.queryForObject("select count(*) from gallery_board where title like ?",
        Integer.class, "%" + search + "%");
  }

  public void upViews(int id) {
    jdbcTemplate.update("update gallery_board set views = views+1 where id = ?", id);
  }

  public void delete(int id) {
    jdbcTemplate.update("delete from gallery_board where id = ?", id);
  }

}
