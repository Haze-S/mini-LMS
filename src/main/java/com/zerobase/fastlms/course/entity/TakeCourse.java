package com.zerobase.fastlms.course.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class TakeCourse implements TakeCourseCode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  long courseId;
  String userId;

  long payPrice;
  String status;

  LocalDateTime regDt;
}
