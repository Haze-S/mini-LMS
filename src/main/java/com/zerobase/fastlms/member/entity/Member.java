package com.zerobase.fastlms.member.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member implements MemberCode {

  @Id
  private String userId;

  private String userName;
  private String phoneNumber;
  private String password;
  private LocalDateTime regDt;

  private String emailAuthKey;
  private LocalDateTime emailAuthDt;
  private boolean emailAuthYn;

  private String resetPasswordKey;
  private LocalDateTime resetPasswordLimitDt;

  private boolean adminYn;

  private String userStatus;
}
