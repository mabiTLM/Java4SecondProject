package com.nanoClone.projectSecond.groupMissonStatement.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.groupMissonStatement.domain.GroupMissionStatement;
import com.nanoClone.projectSecond.groupMissonStatement.service.GroupMissionStatementService;
import jakarta.servlet.http.HttpSession;

@Controller
public class GroupMissionStatementController {

  @Autowired
  GroupMissionStatementService groupMissionStatementService;

  @GetMapping("/groupMissionStatement")
  public String groupMissionStatementPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Group mission statement");
    model.addAttribute("path", "/researches/groupMissionStatement");
    model.addAttribute("content", "groupMissionStatementFragment");
    model.addAttribute("contentHead", "groupMissionStatementFragmentHead");
    model.addAttribute("bannerBundle", "Researches");
    model.addAttribute("banner", "Group mission statement");

    try {
      model.addAttribute("groupMissionStatement", groupMissionStatementService.get());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "/basic/layout";
  }

  @GetMapping("/groupMissionStatement/add")
  public String groupMissionStatementAddPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Group mission statement");
    model.addAttribute("path", "/researches/groupMissionStatementAdd");
    model.addAttribute("content", "groupMissionStatementAddFragment");
    model.addAttribute("contentHead", "groupMissionStatementAddFragmentHead");
    model.addAttribute("bannerBundle", "Researches");
    model.addAttribute("banner", "Group mission statement");

    try {
      model.addAttribute("groupMissionStatement", groupMissionStatementService.get().getContent());
      model.addAttribute("groupMissionStatementId", groupMissionStatementService.get().getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "/basic/layout";
  }

  @PostMapping("/groupMissionStatement/add")
  public String groupMissionStatementAddPagePost(Model model,
      @RequestParam Map<String, String> data, HttpSession session) {
    try {
      if (Integer.parseInt(session.getAttribute("isManager").toString()) == 1) {
        GroupMissionStatement tempGroupMissionStatement = new GroupMissionStatement();
        try {
          String tempContent = data.get("content");
          tempContent = tempContent.replaceAll("width=\"[0-9]*\"", "width=\"100%\"");
          tempContent = tempContent.replaceAll("height=\"[0-9]*\"", "height=\"auto\"");
          tempContent = tempContent.replace("\n", "<br />");
          tempGroupMissionStatement.setContent(tempContent);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          tempGroupMissionStatement.setId(Integer.parseInt(data.get("id")));
        } catch (Exception e) {
          e.printStackTrace();
        }
        groupMissionStatementService.add(tempGroupMissionStatement);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/groupMissionStatement";
  }
}
