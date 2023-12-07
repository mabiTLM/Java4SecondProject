package com.nanoClone.projectSecond.professor.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.members.domain.Members;
import com.nanoClone.projectSecond.members.service.MembersService;
import com.nanoClone.projectSecond.university.service.UniversityService;

@Controller
public class ProfessorController {
  @Autowired
  MembersService membersService;

  @Autowired
  UniversityService universityService;

  @GetMapping("/professor")
  public String professorPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Professor");
    model.addAttribute("path", "/professor/index");
    model.addAttribute("content", "professorFragment");
    model.addAttribute("contentHead", "professorFragmentHead");
    model.addAttribute("bannerBundle", "Professor");
    model.addAttribute("banner", "Professor");
    model.addAttribute("professorList", membersService.getAllProfessor());
    Members tempMember;
    if (data.get("professorChoice") != null) {
      tempMember = membersService.get(Integer.parseInt(data.get("professorChoice")));
    } else {
      tempMember = membersService.getLastProfessor();
    }
    tempMember.setInfo(tempMember.getInfo().replace("\n", "<br />"));
    model.addAttribute("choice", tempMember);

    model.addAttribute("universityList", universityService.getAll());
    return "/basic/layout";
  }
}
