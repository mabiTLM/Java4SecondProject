package com.nanoClone.projectSecond.instruments.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Instruments {
  private int id;
  @NonNull
  private String title;
  @NonNull
  private String image;

}
