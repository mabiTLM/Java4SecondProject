package com.nanoClone.projectSecond.careers.domain;

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
public class Careers {
  private int id;
  private int university;
  @NonNull
  private int memberId;
  @NonNull
  private String title;
  private String content;
  private Date start;
  private Date end;
  private String universityName;
}
