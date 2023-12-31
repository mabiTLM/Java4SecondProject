package com.nanoClone.projectSecond.lectures.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Lectures {
  private int id;
  @NonNull
  private String name;
  @NonNull
  private String lectureWhen;

}
