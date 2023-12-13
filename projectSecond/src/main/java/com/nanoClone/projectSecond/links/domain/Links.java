package com.nanoClone.projectSecond.links.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Links {
  private int id;
  @NonNull
  private String category;
  @NonNull
  private String image;
  @NonNull
  private String link;
}
