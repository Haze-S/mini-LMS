package com.zerobase.fastlms.main.controller;

import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

  private final MailComponents mailComponents;

  @RequestMapping("/")
  public String index() {

//        mailComponents.sendMail();

    return "index";
  }

  @RequestMapping("/error/denied")
  public String errorDenied() {

//        mailComponents.sendMail();

    return "error/denied";
  }

}
