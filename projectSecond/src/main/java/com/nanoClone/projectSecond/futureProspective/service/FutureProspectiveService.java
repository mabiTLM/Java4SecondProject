package com.nanoClone.projectSecond.futureProspective.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.futureProspective.dao.FutureProspectiveDAO;
import com.nanoClone.projectSecond.futureProspective.domain.FutureProspective;

@Service
public class FutureProspectiveService {

  @Autowired
  FutureProspectiveDAO futureProspectiveDAO;

  public void add(FutureProspective futureProspective) {
    try {
      if (futureProspectiveDAO.get() != null) {
        futureProspectiveDAO.update(futureProspective);
      }
    } catch (Exception e) {
      futureProspectiveDAO.add(futureProspective);
    }
  }

  public FutureProspective get() {
    return futureProspectiveDAO.get();
  }
}
