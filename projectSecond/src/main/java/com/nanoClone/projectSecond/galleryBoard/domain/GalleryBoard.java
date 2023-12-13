package com.nanoClone.projectSecond.galleryBoard.domain;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class GalleryBoard {
  private int id;
  @NonNull
  private int memberId;
  @NonNull
  private String title;
  private String content;
  private Date createdAt;
  private int views;
  private String memberName;
}
