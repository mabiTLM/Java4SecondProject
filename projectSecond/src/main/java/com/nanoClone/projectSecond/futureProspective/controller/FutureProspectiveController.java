package com.nanoClone.projectSecond.futureProspective.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.futureProspective.domain.FutureProspective;
import com.nanoClone.projectSecond.futureProspective.service.FutureProspectiveService;
import jakarta.servlet.http.HttpSession;

@Controller
public class FutureProspectiveController {
  @Autowired
  FutureProspectiveService futureProspectiveService;

  @GetMapping("/future_prospective")
  public String futurePage(Model model) {
    model.addAttribute("title", "Future Prospective");
    model.addAttribute("path", "/members/futureProspective");
    model.addAttribute("content", "futureProspectiveFragment");
    model.addAttribute("contentHead", "futureProspectiveFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Future prospective");
    try {
      model.addAttribute("future", futureProspectiveService.get());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "/basic/layout";
  }

  @GetMapping("/future_prospective/add")
  public String futurePageAdd(Model model) {
    model.addAttribute("title", "Future Prospective");
    model.addAttribute("path", "/members/futureProspectiveAdd");
    model.addAttribute("content", "futureProspectiveAddFragment");
    model.addAttribute("contentHead", "futureProspectiveAddFragmentHead");
    model.addAttribute("bannerBundle", "Members");
    model.addAttribute("banner", "Future prospective");
    try {
      FutureProspective tempFutureProspective = futureProspectiveService.get();
      tempFutureProspective.setContent(tempFutureProspective.getContent().replace("\n", "<br />"));
      model.addAttribute("futureContent", tempFutureProspective.getContent());
      model.addAttribute("futureId", tempFutureProspective.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "/basic/layout";
  }

  @PostMapping("/future_prospective/add")
  public String futurePageAddPost(Model model, @RequestParam Map<String, String> data,
      HttpSession session) {
    try {
      if (Integer.parseInt(session.getAttribute("isManager").toString()) == 1) {
        FutureProspective tempfutureProspective = new FutureProspective();
        try {
          String tempContent = data.get("content");
          tempContent = tempContent.replaceAll("width=\"[0-9]*\"", "width=\"100%\"");
          tempContent = tempContent.replaceAll("height=\"[0-9]*\"", "height=\"auto\"");
          tempContent = tempContent.replace("\n", "<br />");
          tempfutureProspective.setContent(tempContent);
        } catch (Exception e) {
          e.printStackTrace();
        }
        try {
          tempfutureProspective.setId(Integer.parseInt(data.get("id")));
        } catch (Exception e) {
          e.printStackTrace();
        }
        futureProspectiveService.add(tempfutureProspective);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/future_prospective";
  }
}
