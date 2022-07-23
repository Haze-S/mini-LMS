package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

  boolean register(MemberInput parameter);

  /**
   * uuid에 해당하는 계정을 활성화 함.
   *
   * @param uuid
   */
  boolean emailAuth(String uuid);

  /**
   * 입력한 이메일로 비밀번호 초기화 정보 전송
   *
   * @param parameter
   */
  boolean sendResetPassword(ResetPasswordInput parameter);

  /**
   * 입력받은 uuid에 대해서 password로 초기화
   *
   * @param uuid
   * @param password
   */
  boolean resetPassword(String uuid, String password);

  /**
   * 입력받은 uuid값이 유효한지 체크
   *
   * @param uuid
   */
  boolean checkResetPassword(String uuid);

  /**
   * 회원 목록 리턴 어드민에서만 사용 가능
   *
   * @param parameter
   */
  List<MemberDto> list(MemberParam parameter);

  /**
   * 회원 상세 정보
   *
   * @param userId
   */
  MemberDto detail(String userId);

  /**
   * 회원 상태 변경
   *
   * @param userId
   * @param userStatus
   */
  boolean updateStatus(String userId, String userStatus);

  /**
   * 회원 비밀번호 초기화
   *
   * @param userId
   * @param password
   */
  boolean updatePassword(String userId, String password);

  /**
   * 회원 정보 페이지내 비밀번호 변경
   *
   * @param parameter
   */
  ServiceResult updateMemberPassword(MemberInput parameter);

  /**
   * 회원 정보 수정
   *
   * @param memberInput
   */
  ServiceResult updateMember(MemberInput memberInput);

  /**
   * 회원 탈퇴
   *
   * @param userId
   */
  ServiceResult withdraw(String userId, String password);
}
