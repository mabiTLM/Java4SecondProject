package com.nanoClone.projectSecond.researchTopics.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.researchTopics.domain.ResearchTopics;
import com.nanoClone.projectSecond.researchTopics.service.ResearchTopicsService;

@Controller
public class ResearchTopicsController {
  @Autowired
  ResearchTopicsService researchTopicsService;

  @GetMapping("/researchTopics")
  public String researchTopicsPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Research topics");
    model.addAttribute("path", "/researches/researchTopics");
    model.addAttribute("content", "researchTopicsFragment");
    model.addAttribute("contentHead", "researchTopicsFragmentHead");
    model.addAttribute("bannerBundle", "Researches");
    model.addAttribute("banner", "Research topics");

    int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
    model.addAttribute("page", page);
    List<ResearchTopics> tempList = null;
    try {
      if (page == 1) {
        tempList = researchTopicsService.getAll("topic");
      } else if (page == 2) {
        tempList = researchTopicsService.getAll("COC");
      } else if (page == 3) {
        tempList = researchTopicsService.getAll("CCN");
      } else if (page == 4) {
        tempList = researchTopicsService.getAll("other");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("list", tempList);

    return "/basic/layout";
  }

  @GetMapping("/researchTopics/add")
  public String researchTopicsAddPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Research topics");
    model.addAttribute("path", "/researches/researchTopicsAdd");
    model.addAttribute("content", "researchTopicsAddFragment");
    model.addAttribute("contentHead", "researchTopicsAddFragmentHead");
    model.addAttribute("bannerBundle", "Researches");
    model.addAttribute("banner", "Research topics");
    return "/basic/layout";
  }

  @PostMapping("/researchTopics/add")
  public String researchTopicsAddPagePost(Model model, @RequestParam Map<String, String> data) {
    ResearchTopics tempResearchTopics = new ResearchTopics(data.get("category"));
    tempResearchTopics.setTitle(data.get("title"));
    tempResearchTopics.setContent(data.get("content").replace("\n", "<br />"));
    researchTopicsService.add(tempResearchTopics);

    return "redirect:/researchTopics";
  }
}
