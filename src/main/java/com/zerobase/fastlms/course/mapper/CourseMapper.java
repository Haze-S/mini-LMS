package com.zerobase.fastlms.course.mapper;

import com.zerobase.fastlms.course.CourseParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {


  long selectListCount(CourseParam parameter);
  List<CourseDto> selectList(CourseParam parameter);
}
