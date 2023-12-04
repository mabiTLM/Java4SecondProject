package com.nanoClone.projectSecond.intro.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IntroController {
  @GetMapping("/")
  public String introPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "고분자 나노소재 연구실");
    model.addAttribute("path", "/intro/index");
    model.addAttribute("content", "introFragment");
    model.addAttribute("contentHead", "introFragmentHead");
    return "/basic/layout";
  }
}
