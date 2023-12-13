package com.nanoClone.projectSecond.patents.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.patents.dao.PatentsDAO;
import com.nanoClone.projectSecond.patents.domain.Patents;

@Service
public class PatentsService {
  @Autowired
  PatentsDAO patentsDAO;

  public List<Patents> getAll() {
    return patentsDAO.getAll();
  }

  public void add(Patents patents) {
    patentsDAO.add(patents);
  }

}
