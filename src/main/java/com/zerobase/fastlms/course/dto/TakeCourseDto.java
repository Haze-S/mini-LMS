package com.zerobase.fastlms.course.dto;

import com.zerobase.fastlms.course.entity.TakeCourse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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

  public static TakeCourseDto of(TakeCourse x) {
    return TakeCourseDto.builder()
        .id(x.getId())
        .courseId(x.getCourseId())
        .userId(x.getUserId())
        .payPrice(x.getPayPrice())
        .status(x.getStatus())
        .regDt(x.getRegDt())
        .build();
  }

  public String getRegDtText() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    return regDt != null ? regDt.format(formatter) : "";
  }
}

