package com.nanoClone.projectSecond.lectures.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LecturesController {

  @GetMapping("/lectures")
  public String LecturesPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Lectures");
    model.addAttribute("path", "/lectures/index");
    model.addAttribute("content", "lecturesFragment");
    model.addAttribute("contentHead", "lecturesFragmentHead");
    model.addAttribute("bannerBundle", "Lectures");
    model.addAttribute("banner", "Lectures");
    return "/basic/layout";
  }
}
