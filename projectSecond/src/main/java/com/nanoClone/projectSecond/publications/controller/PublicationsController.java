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
    int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
    model.addAttribute("page", page);


    model.addAttribute("title", "Journals");
    model.addAttribute("path", "/publications/journals");
    model.addAttribute("content", "journalsFragment");
    model.addAttribute("contentHead", "journalsFragmentHead");
    model.addAttribute("bannerBundle", "Publications");
    model.addAttribute("banner", "Publications");
    return "/basic/layout";
  }

}
