package com.nanoClone.projectSecond.researchTopics.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.researchTopics.dao.ResearchTopicsDAO;
import com.nanoClone.projectSecond.researchTopics.domain.ResearchTopics;

@Service
public class ResearchTopicsService {
  @Autowired
  ResearchTopicsDAO researchTopicsDAO;

  public List<ResearchTopics> getAll(String category) {
    return researchTopicsDAO.getAll(category);
  }

  public void add(ResearchTopics researchTopics) {
    researchTopicsDAO.add(researchTopics);
  }

}
