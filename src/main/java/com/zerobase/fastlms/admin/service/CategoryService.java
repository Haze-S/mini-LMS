package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.CategoryDto;
import com.zerobase.fastlms.admin.model.CategoryInput;
import java.util.List;

public interface CategoryService {

  /**
   * 카테고리 리스트 출력
   */
  List<CategoryDto> list();

  /**
   * 카테고리 신규 추가
   *
   * @param categoryName
   */
  boolean add(String categoryName);

  /**
   * 카테고리 수정
   *
   * @param parameter
   */
  boolean update(CategoryInput parameter);

  /**
   * 카테고리 삭제
   *
   * @param id
   */
  boolean del(long id);

}
