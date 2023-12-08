package com.nanoClone.projectSecond.newsBoard.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.newsBoard.dao.NewsBoardDAO;
import com.nanoClone.projectSecond.newsBoard.domain.NewsBoard;

@Service
public class NewsBoardService {
  @Autowired
  NewsBoardDAO newsBoardDAO;

  public void add(NewsBoard newsBoard) {
    newsBoardDAO.add(newsBoard);
  }

  public List<NewsBoard> getAll(int page, int count) {
    return newsBoardDAO.getAll((page - 1) * count, count);
  }

  public NewsBoard get(int newsBoardId) {
    return newsBoardDAO.get(newsBoardId);
  }

  public int getPageCount(int count) {
    return (newsBoardDAO.getCount() - 1) / count + 1;
  }

  public void upViews(int id) {
    newsBoardDAO.upViews(id);
  }

  public void delete(int id) {
    newsBoardDAO.delete(id);
  }

  public void edit(NewsBoard newsBoard) {
    newsBoardDAO.edit(newsBoard);
  }
}
