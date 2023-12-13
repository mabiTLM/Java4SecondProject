package com.nanoClone.projectSecond.collaborations.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Collaborations {
  private int id;
  @NonNull
  private String category;
  @NonNull
  private String name;
  private String university;
}
