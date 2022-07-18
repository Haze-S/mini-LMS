package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.CourseParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import java.util.List;

public interface CourseService {

  /**
   * 강좌 등록
   * @param courseInput
   */
  boolean add (CourseInput courseInput);

  /**
   * 강좌 목록
   * @param parameter
   */
  List<CourseDto> list(CourseParam parameter);
}
