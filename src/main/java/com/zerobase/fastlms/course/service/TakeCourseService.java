package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import java.util.List;

public interface TakeCourseService {

  /**
   * 수강 목록
   *
   * @param parameter
   */
  List<TakeCourseDto> list(TakeCourseParam parameter);

  /**
   * 수강 상세 정보
   *
   * @param id
   */
  TakeCourseDto detail(long id);

  /**
   * 수강내용 상태 변경
   *
   * @param id
   * @param status
   */
  ServiceResult updateStatus(long id, String status);

  /**
   * 내 수강내역 목록
   *
   * @param userId
   */
  List<TakeCourseDto> myCourse(String userId);

  /**
   * 수강신청 취소
   *
   * @param id
   */
  ServiceResult cancel(long id);
}
