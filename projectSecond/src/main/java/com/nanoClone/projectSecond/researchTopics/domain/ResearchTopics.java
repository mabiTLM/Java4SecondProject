package com.nanoClone.projectSecond.researchTopics.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ResearchTopics {
  private int id;
  @NonNull
  private String category;
  private String title;
  private String content;
}
