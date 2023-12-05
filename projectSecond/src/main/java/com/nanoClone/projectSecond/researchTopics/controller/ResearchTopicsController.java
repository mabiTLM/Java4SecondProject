package com.nanoClone.projectSecond.researchTopics.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResearchTopicsController {
  @GetMapping("/groupMissionStatement")
  public String groupMissionStatementPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Group mission statement");
    model.addAttribute("path", "/researches/groupMissionStatement");
    model.addAttribute("content", "groupMissionStatementFragment");
    model.addAttribute("contentHead", "groupMissionStatementFragmentHead");
    model.addAttribute("bannerBundle", "Researches");
    model.addAttribute("banner", "Group mission statement");
    return "/basic/layout";
  }

  @GetMapping("/researchTopics")
  public String researchTopicsPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Research topics");
    model.addAttribute("path", "/researches/researchTopics");
    model.addAttribute("content", "researchTopicsFragment");
    model.addAttribute("contentHead", "researchTopicsFragmentHead");
    model.addAttribute("bannerBundle", "Researches");
    model.addAttribute("banner", "Research topics");
    return "/basic/layout";
  }

  @GetMapping("/instruments")
  public String instrumentsPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Instruments");
    model.addAttribute("path", "/researches/instruments");
    model.addAttribute("content", "instrumentsFragment");
    model.addAttribute("contentHead", "instrumentsFragmentHead");
    model.addAttribute("bannerBundle", "Researches");
    model.addAttribute("banner", "Instruments");
    return "/basic/layout";
  }
}
