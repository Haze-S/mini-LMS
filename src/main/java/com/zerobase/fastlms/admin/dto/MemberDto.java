package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
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

    boolean emailAuthYn;
    LocalDateTime emailAuthDt;
    String emailAuthKey;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    boolean adminYn;
    String userStatus;

    long totalCount;
    long seq;

    public static MemberDto of(Member member) {

        return MemberDto.builder()
            .userId(member.getUserId())
            .userName(member.getUserName())
            .phone(member.getPhoneNumber())
            .password(member.getPassword())
            .regDt(member.getRegDt())
            .emailAuthYn(member.isEmailAuthYn())
            .emailAuthDt(member.getEmailAuthDt())
            .emailAuthKey(member.getEmailAuthKey())
            .resetPasswordKey(member.getResetPasswordKey())
            .resetPasswordLimitDt(member.getResetPasswordLimitDt())
            .adminYn(member.isAdminYn())
            .userName(member.getUserStatus())
            .build();
    }
}

