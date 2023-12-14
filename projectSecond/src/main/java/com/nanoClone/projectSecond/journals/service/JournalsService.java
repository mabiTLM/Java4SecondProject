package com.nanoClone.projectSecond.journals.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.journals.dao.JournalsDAO;
import com.nanoClone.projectSecond.journals.domain.Journals;

@Service
public class JournalsService {
  @Autowired
  JournalsDAO journalsDAO;

  public void add(Journals journals) {
    journalsDAO.add(journals);
  }

  public List<Journals> getAll() {
    return journalsDAO.getAll();
  }

  public List<Journals> getAll(int page, int count) {
    return journalsDAO.getAll((page - 1) * count, count);
  }

  public List<Journals> getSelect(int id) {
    return journalsDAO.getIsSelect(id);
  }
}
