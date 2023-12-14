package com.nanoClone.projectSecond.collaborations.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nanoClone.projectSecond.collaborations.domain.Collaborations;
import com.nanoClone.projectSecond.collaborations.service.CollaborationsService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CollaborationsController {
  @Autowired
  CollaborationsService collaborationsService;

  @GetMapping("/collaborations")
  public String collaborationsPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Collaborations");
    model.addAttribute("path", "/links/collaborations");
    model.addAttribute("content", "collaborationsFragment");
    model.addAttribute("contentHead", "collaborationsFragmentHead");
    model.addAttribute("bannerBundle", "Links");
    model.addAttribute("banner", "Collaborations");
    try {
      model.addAttribute("unistList", collaborationsService.getAllCategory("unist"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      model.addAttribute("outsideList", collaborationsService.getAllCategory("outside"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "/basic/layout";
  }

  @PostMapping("/collaborations")
  public String collaborationsPage(@RequestParam Map<String, String> data, HttpSession session,
      RedirectAttributes redirectAttributes) {
    Collaborations tempCollaborations = new Collaborations(data.get("category"), data.get("name"));
    tempCollaborations.setUniversity(data.get("university"));
    collaborationsService.add(tempCollaborations);
    return "redirect:/collaborations";
  }

  @PostMapping("/collaborations/delete")
  public String collaborationsDeletePage(@RequestParam Map<String, String> data,
      HttpSession session, RedirectAttributes redirectAttributes) {
    collaborationsService.delete(Integer.parseInt(data.get("id")));
    return "redirect:/collaborations";
  }
}
