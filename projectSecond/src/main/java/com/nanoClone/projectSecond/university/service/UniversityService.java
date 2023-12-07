package com.nanoClone.projectSecond.university.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.university.dao.UniversityDAO;
import com.nanoClone.projectSecond.university.domain.University;

@Service
public class UniversityService {
  @Autowired
  UniversityDAO universityDAO;

  public List<University> getAll() {
    return universityDAO.getAll();
  }
}
