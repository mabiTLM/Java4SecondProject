package com.nanoClone.projectSecond.careers.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.careers.dao.CareersDAO;
import com.nanoClone.projectSecond.careers.domain.Careers;

@Service
public class CareersService {
  @Autowired
  CareersDAO careersDAO;

  public List<Careers> get(int memberId) {
    return careersDAO.get(memberId);
  }

  public void add(Careers careers) {
    careersDAO.add(careers);
  }

  public List<Careers> getAll() {
    return careersDAO.getAll();
  }

  public void delete(int id) {
    careersDAO.delete(id);
  }
}
