package com.nanoClone.projectSecond.members.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Members {
  private int id;
  @NonNull
  private String email;
  @NonNull
  private String userId;
  @NonNull
  private String password;
  private String category;
  private String englishName;
  private String name;
  private String position;
  private String info;
  private String image;
  private int isManager;



}
