package com.zerobase.fastlms.course.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TakeCourseDto {

  long id;

  long courseId;
  String userId;

  long payPrice;
  String status;

  LocalDateTime regDt;

  String userName;
  String phone;
  String subject;

  long totalCount;
  long seq;
}

