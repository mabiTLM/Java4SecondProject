package com.nanoClone.projectSecond.alumni.domain;

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
public class Alumni {
  private int id;
  @NonNull
  private int memberId;
  private String content;
  private Date start;
  private Date end;

}
