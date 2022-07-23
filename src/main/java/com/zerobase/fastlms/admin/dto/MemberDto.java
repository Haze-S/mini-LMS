package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.member.entity.Member;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDto {

  String userId;
  String userName;
  String phone;
  String password;
  LocalDateTime regDt;
  LocalDateTime udtDt;

  boolean emailAuthYn;
  LocalDateTime emailAuthDt;
  String emailAuthKey;

  String resetPasswordKey;
  LocalDateTime resetPasswordLimitDt;

  boolean adminYn;
  String userStatus;

  long totalCount;
  long seq;

  private String zipcode;
  private String addr;
  private String addrDetail;

  public static MemberDto of(Member member) {

    return MemberDto.builder()
        .userId(member.getUserId())
        .userName(member.getUserName())
        .phone(member.getPhoneNumber())
        .password(member.getPassword())
        .regDt(member.getRegDt())
        .udtDt(member.getUdtDt())
        .emailAuthYn(member.isEmailAuthYn())
        .emailAuthDt(member.getEmailAuthDt())
        .emailAuthKey(member.getEmailAuthKey())
        .resetPasswordKey(member.getResetPasswordKey())
        .resetPasswordLimitDt(member.getResetPasswordLimitDt())
        .adminYn(member.isAdminYn())
        .userName(member.getUserStatus())
        .zipcode(member.getZipcode())
        .addr(member.getAddr())
        .addrDetail(member.getAddrDetail())
        .build();
  }

  public String getRegDtText() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    return regDt != null ? regDt.format(formatter) : "";
  }

  public String getUdtDtText() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    return udtDt != null ? udtDt.format(formatter) : "";
  }
}

