package com.nanoClone.projectSecond.newsBoard.domain;

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
public class NewsBoard {
  private int id;
  @NonNull
  private String category;
  @NonNull
  private String title;
  @NonNull
  private String content;
  private Date createdAt;
  private int views;
}
