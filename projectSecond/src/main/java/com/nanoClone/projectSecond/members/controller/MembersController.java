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
import jakarta.servlet.http.HttpSession;

@Controller
public class MembersController {
  @Autowired
  MembersService membersService;

  int count = 10;

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
    try {
      Members tempMember = new Members(data.get("email"), data.get("email"), data.get("password"));
      tempMember.setName(data.get("name"));
      tempMember.setEnglishName(data.get("english_name"));
      tempMember.setPosition(data.get("position"));
      membersService.add(tempMember);
      return "redirect:/login";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/join";
  }

  @GetMapping("/login")
  public String loginPage() {
    return "/manager/login";
  }

  @PostMapping("/login")
  public String loginPagePost(@RequestParam Map<String, String> data, HttpSession session) {
    Members tempMember = new Members();
    tempMember.setUserId(data.get("userId"));
    tempMember.setPassword(data.get("password"));
    tempMember = membersService.login(tempMember);

    if (tempMember != null) {
      session.setAttribute("isLogin", tempMember);
      session.setAttribute("isManager", tempMember.getIsManager());
    }
    return "redirect:/";
  }

  @GetMapping("/logout")
  public String logout(@RequestParam Map<String, String> data, HttpSession session) {
    session.setAttribute("isLogin", null);
    return "redirect:/";
  }

  @GetMapping("/manager")
  public String managerPage(Model model, HttpSession session,
      @RequestParam Map<String, String> data) {
    if (session.getAttribute("isLogin") != null
        && Integer.parseInt(session.getAttribute("isManager").toString()) == 1) {
      int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
      model.addAttribute("list", membersService.getAll(page, count));
      model.addAttribute("pageCount", membersService.getPageCount(count));

      if (data.get("id") != null) {
        model.addAttribute("choiceMember", membersService.get(Integer.parseInt(data.get("id"))));
      }
      return "/manager/index";
    } else {
      return "redirect:/";
    }
  }

  @PostMapping("/manager")
  public String managerPagePost(Model model, HttpSession session,
      @RequestParam Map<String, String> data) {
    if (session.getAttribute("isLogin") != null
        && Integer.parseInt(session.getAttribute("isManager").toString()) == 1) {
      Members tempMember = membersService.get(Integer.parseInt(data.get("id")));
      tempMember.setCategory(data.get("category"));
      tempMember.setUserId(data.get("userId"));
      tempMember.setEnglishName(data.get("englishName"));
      tempMember.setName(data.get("name"));
      tempMember.setPosition(data.get("position"));
      tempMember.setInfo(data.get("info"));
      tempMember.setEmail(data.get("email"));
      tempMember.setChineseName(data.get("chineseName"));
      tempMember.setAddress(data.get("address"));
      tempMember.setPhone(data.get("phone"));
      tempMember.setSite(data.get("site"));
      tempMember.setLink(data.get("link"));
      membersService.update(tempMember);

      return "redirect:/manager";
    } else {
      return "redirect:/";
    }
  }

}
