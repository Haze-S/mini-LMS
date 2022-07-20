package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;
import java.util.List;

public interface CourseService {

  /**
   * 강좌 등록
   *
   * @param courseInput
   */
  boolean add(CourseInput courseInput);

  /**
   * 강좌 정보수정
   *
   * @param parameter
   */
  boolean set(CourseInput parameter);

  /**
   * 강좌 목록
   *
   * @param parameter
   */
  List<CourseDto> list(CourseParam parameter);

  /**
   * 강좌 상세정보
   *
   * @param id
   */
  CourseDto getById(long id);

  /**
   * 강좌 내용 삭제
   *
   * @param idList
   */
  boolean del(String idList);

  /**
   * 프론트 강좌 목록
   *
   * @param parameter
   */
  List<CourseDto> frontList(CourseParam parameter);

  /**
   * 프론트 강좌 상세 정보
   *
   * @param id
   */
  CourseDto frontDetail(long id);

  /**
   * 수강신청
   *
   * @param parameter
   */
  ServiceResult req(TakeCourseInput parameter);
}
