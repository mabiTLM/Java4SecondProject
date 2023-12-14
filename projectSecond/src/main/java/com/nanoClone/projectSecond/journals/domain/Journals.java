package com.nanoClone.projectSecond.journals.domain;

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
public class Journals {
  private int id;
  @NonNull
  private int selected;
  @NonNull
  private Date makeDate;
  @NonNull
  private String title;
  @NonNull
  private String maker;
  @NonNull
  private String bookName;
  private String volume;
  private String page;
  private String impactFactor;
  private String image;
  private String file;
  private String link;
}
