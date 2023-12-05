package com.nanoClone.projectSecond.professor.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfessorController {
  @GetMapping("/professor")
  public String professorPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Professor");
    model.addAttribute("path", "/professor/index");
    model.addAttribute("content", "professorFragment");
    model.addAttribute("contentHead", "professorFragmentHead");
    model.addAttribute("bannerBundle", "Professor");
    model.addAttribute("banner", "Professor");
    return "/basic/layout";
  }
}
