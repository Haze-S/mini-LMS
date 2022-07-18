package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.CategoryDto;
import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.admin.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

  private final CategoryRepository categoryRepository;

  private Sort getSortBySortValueDesc() {
    return Sort.by(Direction.DESC, "sortValue");
  }

  @Override
  public List<CategoryDto> list() {
    List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());
    return CategoryDto.of(categories);
  }

  @Override
  public boolean add(String categoryName) {

    Category category = Category.builder()
        .categoryName(categoryName)
        .usingYn(true)
        .sortValue(0)
        .build();
    categoryRepository.save(category);

    return true;
  }

  @Override
  public boolean update(CategoryInput parameter) {

    Optional<Category> optionalCategory = categoryRepository.findById(parameter.getId());

    if (optionalCategory.isPresent()) {
      Category category = optionalCategory.get();
      category.setCategoryName(parameter.getCategoryName());
      category.setSortValue(parameter.getSortValue());
      category.setUsingYn(parameter.isUsingYn());
      categoryRepository.save(category);
    }

    return true;
  }

  @Override
  public boolean del(long id) {
    categoryRepository.deleteById(id);

    return true;
  }
}
