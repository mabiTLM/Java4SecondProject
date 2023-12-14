package com.nanoClone.projectSecond.journals.service;

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

}