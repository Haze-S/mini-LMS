package com.zerobase.fastlms.course.controller;

import static java.time.LocalDate.now;

import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.service.CourseService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminCourseController extends BaseController {

  private final CourseService courseService;
  private final CategoryService categoryService;

  @GetMapping("/admin/course/list")
  public String list(Model model, CourseParam parameter) {

    parameter.init();

    List<CourseDto> courseList = courseService.list(parameter);

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

    return "/admin/course/list";
  }

  @GetMapping(value = {"/admin/course/add", "/admin/course/edit"})
  public String add(Model model, HttpServletRequest request, CourseInput parameter) {

    model.addAttribute("category", categoryService.list());

    boolean editMode = request.getRequestURI().contains("/edit");
    CourseDto detail = new CourseDto();

    if (editMode) {
      long id = parameter.getId();
      CourseDto existCourse = courseService.getById(id);
      if (existCourse == null) {
        model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
        return "common/error";
      }
      detail = existCourse;
    }

    model.addAttribute("editMode", editMode);
    model.addAttribute("detail", detail);

    return "/admin/course/add";
  }

  private String[] getNewSaveFile(String baseLocalPath, String baseUrlPath,
      String originalFileName) {

    String[] dirs = {
        String.format("%s/%d/", baseLocalPath, now().getYear()),
        String.format("%s/%d/%02d", baseLocalPath, now().getYear(), now().getMonthValue()),
        String.format("%s/%d/%02d/%02d", baseLocalPath, now().getYear(), now().getMonthValue(),
            now().getDayOfMonth())
    };

    String urlDir = String.format("%s/%d/%02d/%02d", baseUrlPath, now().getYear(),
        now().getMonthValue(), now().getDayOfMonth());

    for (String dir : dirs) {
      File file = new File(dir);
      if (!file.isDirectory()) {
        file.mkdir();
      }
    }

    String fileExtension = "";
    if (originalFileName != null) {
      int dotPos = originalFileName.lastIndexOf(".");
      if (dotPos > -1) {
        fileExtension = originalFileName.substring(dotPos + 1);
      }
    }

    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    String newFileName = String.format("%s%s", dirs[2], uuid);
    String newUrlFileName = String.format("%s%s", urlDir, uuid);
    if (fileExtension.length() > 0) {
      newFileName += "." + fileExtension;
      newUrlFileName += "." + fileExtension;
    }

    return new String[]{newFileName, newUrlFileName};
  }

  @PostMapping(value = {"/admin/course/add", "/admin/course/edit"})
  public String addSubmit(Model model
      , HttpServletRequest request
      , CourseInput parameter
      , MultipartFile file) {

    String saveFileName = "";
    String urlFileName = "";

    if (file != null) {
      String originalFileName = file.getOriginalFilename();
      String baseLocalPath = "/Users/home/Project/fastlms/files";
      String baseUrlPath = "/files";

      String[] arrFileNames = getNewSaveFile(baseLocalPath, baseUrlPath, originalFileName);

      saveFileName = arrFileNames[0];
      urlFileName = arrFileNames[1];

      try {
        File newFile = new File(saveFileName);
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
      } catch (IOException e) {
        log.info(e.getMessage());
      }

    }

    parameter.setFileName(saveFileName);
    parameter.setUrlFileName(urlFileName);

    boolean editMode = request.getRequestURI().contains("/edit");

    if (editMode) {
      long id = parameter.getId();
      CourseDto existCourse = courseService.getById(id);
      if (existCourse == null) {
        model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
        return "common/error";
      }
      boolean result = courseService.set(parameter);
    } else {
      boolean result = courseService.add(parameter);
    }

    return "redirect:/admin/course/list";
  }

  @PostMapping("/admin/course/delete")
  public String del(Model model, HttpServletRequest request, CourseInput parameter) {

    boolean result = courseService.del(parameter.getIdList());

    return "redirect:/admin/course/list";
  }
}
