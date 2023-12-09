package com.nanoClone.projectSecond.alumni.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.alumni.dao.AlumniDAO;
import com.nanoClone.projectSecond.alumni.domain.Alumni;

@Service
public class AlumniService {
  @Autowired
  AlumniDAO alumniDAO;

  public Alumni get(int meberId) {
    return alumniDAO.get(meberId);
  }

  public void add(Alumni alumni) {
    alumniDAO.add(alumni);
  }

  public void update(Alumni alumni) {
    alumniDAO.update(alumni);
  }

  public List<Alumni> getAll() {
    return alumniDAO.getAll();
  }
}
