package com.nanoClone.projectSecond.lectures.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.lectures.domain.Lectures;
import com.nanoClone.projectSecond.lectures.service.LecturesService;

@Controller
public class LecturesController {
  @Autowired
  LecturesService lecturesService;

  @GetMapping("/lectures")
  public String LecturesPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Lectures");
    model.addAttribute("path", "/lectures/index");
    model.addAttribute("content", "lecturesFragment");
    model.addAttribute("contentHead", "lecturesFragmentHead");
    model.addAttribute("bannerBundle", "Lectures");
    model.addAttribute("banner", "Lectures");

    model.addAttribute("list", lecturesService.getAll());
    return "/basic/layout";
  }

  @PostMapping("/lectures/add")
  public String lecturesAddPage(@RequestParam Map<String, String> data) {

    Lectures lectures = new Lectures(data.get("name"), data.get("lectureWhen"));
    lecturesService.add(lectures);

    return "redirect:/lectures";
  }
}
