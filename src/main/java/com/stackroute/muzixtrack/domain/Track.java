package com.stackroute.muzixtrack.domain;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Track {
  @Id
  private int id;
  private String name;
  private String comments;

}
