package com.nanoClone.projectSecond.members.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.nanoClone.projectSecond.careers.service.CareersService;
import com.nanoClone.projectSecond.members.domain.Members;
import com.nanoClone.projectSecond.members.service.MembersService;
import com.nanoClone.projectSecond.university.service.UniversityService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MembersController {
  @Autowired
  MembersService membersService;

  @Autowired
  CareersService careersService;

  @Autowired
  UniversityService universityService;


  int count = 10;

  @GetMapping("/current")
  public String currentPage(Model model) {
    model.addAttribute("title", "Current");
    model.addAttribute("path", "/members/currentGroupMember");
    model.addAttribute("content", "currentGroupMemberFragment");
    model.addAttribute("contentHead", "currentGroupMemberFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Current group members");
    model.addAttribute("graduateList", membersService.getAll("graduate"));
    model.addAttribute("researchersList", membersService.getAll("researchers"));
    model.addAttribute("undergraduateList", membersService.getAll("undergraduate"));
    model.addAttribute("visitingList", membersService.getAll("visiting"));
    model.addAttribute("administrativeList", membersService.getAll("administrative"));
    model.addAttribute("careersList", careersService.getAll());
    model.addAttribute("universityList", universityService.getAll());
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
    session.setAttribute("isManager", null);
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
      @RequestParam Map<String, String> data, @RequestParam("image") MultipartFile image) {
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


      String originImageName = image.getOriginalFilename();
      String[] tempImageNames = originImageName.split("[.]");
      String extImage = originImageName.substring(originImageName.indexOf("."));
      String randomImageName = UUID.randomUUID() + extImage;
      String saveImagePath =
          "/home/tomcat/apache-tomcat-10.1.17/webapps/ROOT/WEB-INF/classes/static/images/member/upload/"
              + randomImageName;
      String uploadImageUrl = "/images/member/upload/" + randomImageName;
      File saveImageFile = new File(saveImagePath);
      try {
        image.transferTo(saveImageFile);
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      tempMember.setImage(uploadImageUrl);


      membersService.update(tempMember);

      return "redirect:/manager";
    } else {
      return "redirect:/";
    }
  }

}
