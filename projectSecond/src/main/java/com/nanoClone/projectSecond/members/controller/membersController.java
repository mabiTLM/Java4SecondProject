package com.nanoClone.projectSecond.members.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class membersController {
  @GetMapping("/current")
  public String currentPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Current");
    model.addAttribute("path", "/members/currentGroupMember");
    model.addAttribute("content", "currentGroupMemberFragment");
    model.addAttribute("contentHead", "currentGroupMemberFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Current group members");
    return "/basic/layout";
  }

  @GetMapping("/alumni")
  public String alumniPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Alumni");
    model.addAttribute("path", "/members/alumni");
    model.addAttribute("content", "alumniFragment");
    model.addAttribute("contentHead", "alumniFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Alumni");
    return "/basic/layout";
  }

  @GetMapping("/future_prospective")
  public String futurePage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Future Prospective");
    model.addAttribute("path", "/members/futureProspective");
    model.addAttribute("content", "futureProspectiveFragment");
    model.addAttribute("contentHead", "futureProspectiveFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Future prospective");
    return "/basic/layout";
  }
}
