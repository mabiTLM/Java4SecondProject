package com.nanoClone.projectSecond.alumni.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.alumni.domain.Alumni;
import com.nanoClone.projectSecond.alumni.service.AlumniService;
import com.nanoClone.projectSecond.members.domain.Members;
import com.nanoClone.projectSecond.members.service.MembersService;

@Controller
public class AlumniController {
  @Autowired
  MembersService membersService;

  @Autowired
  AlumniService alumniService;

  @GetMapping("/alumni")
  public String alumniPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Alumni");
    model.addAttribute("path", "/members/alumni");
    model.addAttribute("content", "alumniFragment");
    model.addAttribute("contentHead", "alumniFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Alumni");
    List<Members> tempMember = null;
    String tempCategory = null;
    int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
    if (page == 1) {
      tempMember = membersService.getAll("alumni_ph");
      tempCategory = "Ph.D.";
    } else if (page == 2) {
      tempMember = membersService.getAll("alumni_ms");
      tempCategory = "M.S.";
    } else if (page == 3) {
      tempMember = membersService.getAll("alumni_researchers");
      tempCategory = "Researchers";
    } else if (page == 4) {
      tempMember = membersService.getAll("alumni_visit");
      tempCategory = "Undergraduates / Visiting students";
    }
    model.addAttribute("subCategory", tempCategory);
    model.addAttribute("page", page);
    model.addAttribute("memberList", tempMember);
    model.addAttribute("alumniList", alumniService.getAll());
    return "/basic/layout";
  }

  @PostMapping("/alumni/add")
  public String joinPagePost(@RequestParam Map<String, String> data) {
    Alumni tempAlumni = new Alumni(Integer.parseInt(data.get("memberId")));
    tempAlumni.setContent(data.get("content"));
    tempAlumni.setStart(Date.valueOf(data.get("start")));
    tempAlumni.setEnd(Date.valueOf(data.get("end")));
    try {
      if (alumniService.get(Integer.parseInt(data.get("memberId"))) != null) {
        alumniService.update(tempAlumni);
      }
    } catch (Exception e) {
      alumniService.add(tempAlumni);
    }
    return "redirect:/alumni";
  }
}
