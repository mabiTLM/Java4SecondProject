package com.nanoClone.projectSecond.honorsAndAwards.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.honorsAndAwards.dao.HonorsAndAwardsDAO;
import com.nanoClone.projectSecond.honorsAndAwards.domain.HonorsAndAwards;

@Service
public class HonorsAndAwardsService {
  @Autowired
  HonorsAndAwardsDAO honorsAndAwardsDAO;

  public void add(HonorsAndAwards honorsAndAwards) {
    honorsAndAwardsDAO.add(honorsAndAwards);
  }

  public List<HonorsAndAwards> get(int memberId) {
    return honorsAndAwardsDAO.get(memberId);
  }
}
