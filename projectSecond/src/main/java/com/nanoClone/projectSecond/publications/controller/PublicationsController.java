package com.nanoClone.projectSecond.publications.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PublicationsController {
  @GetMapping("/journals")
  public String journalsPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Journals");
    model.addAttribute("path", "/publication/journals");
    model.addAttribute("content", "journalsFragment");
    model.addAttribute("contentHead", "journalsFragmentHead");
    model.addAttribute("bannerBundle", "Publications");
    model.addAttribute("banner", "Publications");
    return "/basic/layout";
  }

  @GetMapping("/patents")
  public String groupMissionStatementPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Patents");
    model.addAttribute("path", "/publication/patents");
    model.addAttribute("content", "patentsFragment");
    model.addAttribute("contentHead", "patentsFragmentHead");
    model.addAttribute("bannerBundle", "Publications");
    model.addAttribute("banner", "Patents");
    return "/basic/layout";
  }
}
