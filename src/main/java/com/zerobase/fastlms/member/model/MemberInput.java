package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberInput {

  public MemberInput() {
  }
  public MemberInput(String userId, String userName, String password, String phone) {
    this.userId = userId;
    this.userName = userName;
    this.password = password;
    this.phone = phone;
  }

  private String userId;
  private String userName;
  private String password;
  private String phone;

  private String newPassword;

  private String zipcode;
  private String addr;
  private String addrDetail;
}
