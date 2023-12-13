package com.nanoClone.projectSecond.patents.domain;

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
public class Patents {
  private int id;
  @NonNull
  private String content;
  private int isRegist;
  private String registNumber;
  private Date registDate;
}
