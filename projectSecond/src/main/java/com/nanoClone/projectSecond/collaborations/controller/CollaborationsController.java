package com.nanoClone.projectSecond.collaborations.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CollaborationsController {
  @GetMapping("/collaborations")
  public String collaborationsPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Collaborations");
    model.addAttribute("path", "/links/collaborations");
    model.addAttribute("content", "collaborationsFragment");
    model.addAttribute("contentHead", "collaborationsFragmentHead");
    model.addAttribute("bannerBundle", "Links");
    model.addAttribute("banner", "Collaborations");
    return "/basic/layout";
  }
}
