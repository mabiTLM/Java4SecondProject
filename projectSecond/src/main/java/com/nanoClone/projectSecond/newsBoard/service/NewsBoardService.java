package com.nanoClone.projectSecond.newsBoard.service;

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
}
