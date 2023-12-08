package com.nanoClone.projectSecond.honorsAndAwards.domain;

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
public class HonorsAndAwards {
  private int id;
  @NonNull
  private int memberId;
  @NonNull
  private String title;
  @NonNull
  private Date awardDate;
  private String contents;
}
