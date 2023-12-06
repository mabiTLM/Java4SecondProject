package com.nanoClone.projectSecond.members.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.members.domain.Members;
import com.nanoClone.projectSecond.members.service.MembersService;

@Controller
public class MembersController {
  @Autowired
  MembersService membersService;

  @GetMapping("/current")
  public String currentPage(Model model) {
    model.addAttribute("title", "Current");
    model.addAttribute("path", "/members/currentGroupMember");
    model.addAttribute("content", "currentGroupMemberFragment");
    model.addAttribute("contentHead", "currentGroupMemberFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Current group members");
    return "/basic/layout";
  }

  @GetMapping("/alumni")
  public String alumniPage(Model model) {
    model.addAttribute("title", "Alumni");
    model.addAttribute("path", "/members/alumni");
    model.addAttribute("content", "alumniFragment");
    model.addAttribute("contentHead", "alumniFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Alumni");
    return "/basic/layout";
  }

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

  @GetMapping("/join")
  public String joinPage() {
    return "/manager/join";
  }

  @PostMapping("/join")
  public String joinPagePost(@RequestParam Map<String, String> data) {
    Members tempMember = new Members(data.get("email"), data.get("email"), data.get("password"));
    tempMember.setName(data.get("name"));
    tempMember.setEnglishName(data.get("english_name"));
    tempMember.setPosition(data.get("position"));
    membersService.add(tempMember);
    return "redirect : /login";
  }

  @GetMapping("/login")
  public String loginPage() {
    return "/manager/login";
  }

  @PostMapping("/login")
  public String loginPagePost(@RequestParam Map<String, String> data) {
    // membersService.login();
    return "redirect : /";
  }

}
