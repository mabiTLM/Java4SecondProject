package com.nanoClone.projectSecond.collaborations.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.collaborations.dao.CollaborationsDAO;
import com.nanoClone.projectSecond.collaborations.domain.Collaborations;

@Service
public class CollaborationsService {
  @Autowired
  CollaborationsDAO collaborationsDAO;

  public void add(Collaborations collaborations) {
    collaborationsDAO.add(collaborations);
  }

  public List<Collaborations> getAllCategory(String category) {
    return collaborationsDAO.getAllCategory(category);
  }

  public void delete(int id) {
    collaborationsDAO.delete(id);
  }
}
