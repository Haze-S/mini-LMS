package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import com.zerobase.fastlms.course.service.TakeCourseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminTakeCourseController extends BaseController {

  private final TakeCourseService courseService;

  @GetMapping("/admin/takecourse/list")
  public String list(Model model, TakeCourseParam parameter) {

    parameter.init();

    List<TakeCourseDto> courseList = courseService.list(parameter);

    long totalCount = 0;

    if (!CollectionUtils.isEmpty(courseList)) {
      totalCount = courseList.get(0).getTotalCount();
    }
    String queryString = parameter.getQueryString();
    String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(),
        parameter.getPageIndex(), queryString);

    model.addAttribute("list", courseList);
    model.addAttribute("totalCount", totalCount);
    model.addAttribute("pager", pagerHtml);

    return "/admin/takecourse/list";
  }

  @PostMapping("/admin/takecourse/status")
  public String status(Model model, TakeCourseParam parameter) {

    ServiceResult result = courseService.updateStatus(parameter.getId(), parameter.getStatus());
    if (!result.isResult()) {
      model.addAttribute("message", result.getMessage());
      return "common/error";
    }

    return "redirect:/admin/takecourse/list";
  }
}