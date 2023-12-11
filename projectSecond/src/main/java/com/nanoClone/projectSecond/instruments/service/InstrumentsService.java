package com.nanoClone.projectSecond.instruments.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.instruments.dao.InstrumentsDAO;
import com.nanoClone.projectSecond.instruments.domain.Instruments;

@Service
public class InstrumentsService {
  @Autowired
  InstrumentsDAO instrumentsDAO;

  public void add(Instruments instruments) {
    instrumentsDAO.add(instruments);
  }

  public List<Instruments> getAll(int page, int count) {
    return instrumentsDAO.getAll((page - 1) * count, count);
  }

  public int getPageCount(int count) {
    return (instrumentsDAO.getCount() - 1) / count + 1;
  }

}
