package com.nanoClone.projectSecond.links.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.links.dao.LinksDAO;
import com.nanoClone.projectSecond.links.domain.Links;

@Service
public class LinksService {
  @Autowired
  LinksDAO linksDAO;

  public void add(Links links) {
    linksDAO.add(links);
  }

  public List<Links> getLinks() {
    return linksDAO.getLinks();
  }

  public List<Links> getFunding() {
    return linksDAO.getFunding();
  }
}
