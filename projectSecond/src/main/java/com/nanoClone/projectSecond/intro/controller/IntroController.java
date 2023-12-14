package com.nanoClone.projectSecond.intro.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.journals.service.JournalsService;
import com.nanoClone.projectSecond.newsBoard.service.NewsBoardService;

@Controller
public class IntroController {

  @Autowired
  NewsBoardService newsBoardService;

  @Autowired
  JournalsService journalsService;

  @GetMapping("/")
  public String introPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "고분자 나노소재 연구실");
    model.addAttribute("path", "/intro/index");
    model.addAttribute("content", "introFragment");
    model.addAttribute("contentHead", "introFragmentHead");

    model.addAttribute("newsList", newsBoardService.getAll(1, 3));
    model.addAttribute("journalsList", journalsService.getAll(1, 3));

    return "/basic/layout";
  }
}
