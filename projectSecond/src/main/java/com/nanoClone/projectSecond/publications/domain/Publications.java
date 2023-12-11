package com.nanoClone.projectSecond.publications.domain;

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
public class Publications {
  private int id;
  private int selected;
  @NonNull
  private Date make_date;
  private String title;
  private String maker;
  private String book_name;
  private String volume;
  private String page;
  private int impactFactor;
  private String image;
  private String file;
  private String link;
}
