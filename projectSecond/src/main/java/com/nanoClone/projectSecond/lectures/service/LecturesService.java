package com.nanoClone.projectSecond.lectures.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.lectures.dao.LecturesDAO;
import com.nanoClone.projectSecond.lectures.domain.Lectures;

@Service
public class LecturesService {
  @Autowired
  LecturesDAO lecturesDAO;

  public void add(Lectures lectures) {
    lecturesDAO.add(lectures);
  }

  public List<Lectures> getAll() {
    return lecturesDAO.getAll();
  }

}
