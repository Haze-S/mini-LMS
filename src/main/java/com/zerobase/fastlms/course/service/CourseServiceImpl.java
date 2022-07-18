package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.course.CourseParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.entity.Course;
import com.zerobase.fastlms.course.mapper.CourseMapper;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.repository.CourseRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  @Override
  public boolean add(CourseInput parameter) {

    Course course = Course.builder()
        .subject(parameter.getSubject())
        .regDt(LocalDateTime.now())
        .build();
    courseRepository.save(course);

    return true;
  }

  @Override
  public List<CourseDto> list(CourseParam parameter) {

    long totalCount = memberMapper.selectListCount(parameter);

    List<MemberDto> list = memberMapper.selectList(parameter);
    if (!CollectionUtils.isEmpty(list)) {
      int i = 0;
      for (MemberDto x : list) {
        x.setTotalCount(totalCount);
        x.setSeq(totalCount - parameter.getPageStart() - i);
        i++;
      }
    }

    return list;
  }
}
