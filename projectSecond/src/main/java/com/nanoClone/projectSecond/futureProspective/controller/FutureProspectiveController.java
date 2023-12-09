package com.nanoClone.projectSecond.futureProspective.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FutureProspectiveController {
  @GetMapping("/future_prospective")
  public String futurePage(Model model) {
    model.addAttribute("title", "Future Prospective");
    model.addAttribute("path", "/members/futureProspective");
    model.addAttribute("content", "futureProspectiveFragment");
    model.addAttribute("contentHead", "futureProspectiveFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Future prospective");
    return "/basic/layout";
  }

  @GetMapping("/future_prospective/add")
  public String futurePageAdd(Model model) {
    model.addAttribute("title", "Future Prospective");
    model.addAttribute("path", "/members/futureProspectiveAdd");
    model.addAttribute("content", "futureProspectiveAddFragment");
    model.addAttribute("contentHead", "futureProspectiveAddFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Future prospective");
    return "/basic/layout";
  }
}
