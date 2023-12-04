package com.nanoClone.projectSecond.newsBoard.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsBoardController {
  @GetMapping("/news")
  public String introPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "News");
    model.addAttribute("path", "/news/index");
    model.addAttribute("content", "newsFragment");
    model.addAttribute("contentHead", "newsFragmentHead");
    model.addAttribute("bannerBundle", "News");
    model.addAttribute("banner", "News");

    return "/basic/layout";
  }
}
