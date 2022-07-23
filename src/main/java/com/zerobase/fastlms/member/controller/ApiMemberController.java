package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.common.model.ResponseResult;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;
import com.zerobase.fastlms.course.service.TakeCourseService;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.member.service.MemberService;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiMemberController {

  private final TakeCourseService takeCourseService;

  @PostMapping("/api/member/course/cancel.api")
  public ResponseEntity<?> cancelCourse(Model model, TakeCourseInput parameter, Principal principal) {

    String userId = principal.getName();

    TakeCourseDto detail = takeCourseService.detail(parameter.getTakeCourseId());
    if (detail == null) {
      ResponseResult result = new ResponseResult(false, "수강신청 정보가 존재하지 않습니다.");
      return ResponseEntity.ok().body(result);
    }

    if (userId == null || userId.equals(detail.getUserId())) {
      ResponseResult result = new ResponseResult(false, "본인의 수강신청 정보만 취소할 수 있습니다.");
      return ResponseEntity.ok().body(result);
    }

    ServiceResult result = takeCourseService.cancel(parameter.getTakeCourseId());
    if (!result.isResult()) {
      ResponseResult responseResult = new ResponseResult(false, result.getMessage());
      return ResponseEntity.ok().body(responseResult);
    }

    ResponseResult responseResult = new ResponseResult(true, result.getMessage());
    return ResponseEntity.ok().body(responseResult);
  }
}
