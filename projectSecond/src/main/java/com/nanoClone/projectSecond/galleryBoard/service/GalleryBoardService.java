package com.nanoClone.projectSecond.galleryBoard.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.galleryBoard.dao.GalleryBoardDAO;
import com.nanoClone.projectSecond.galleryBoard.domain.GalleryBoard;

@Service
public class GalleryBoardService {
  @Autowired
  GalleryBoardDAO galleryBoardDAO;

  public void add(GalleryBoard galleryBoard) {
    galleryBoardDAO.add(galleryBoard);
  }

  public GalleryBoard get(int id) {
    return galleryBoardDAO.get(id);
  }

  public List<GalleryBoard> getAll(int page, int count) {
    return galleryBoardDAO.getAll((page - 1) * count, count);
  }

  public int getPageCount(int count) {
    return (galleryBoardDAO.getCount() - 1) / count + 1;
  }

  public void upViews(int id) {
    galleryBoardDAO.upViews(id);
  }

  public void delete(int id) {
    galleryBoardDAO.delete(id);
  }
}
