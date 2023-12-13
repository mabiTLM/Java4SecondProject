package com.nanoClone.projectSecond.patents.controller;

import java.sql.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.patents.domain.Patents;
import com.nanoClone.projectSecond.patents.service.PatentsService;

@Controller
public class PatentsController {
  @Autowired
  PatentsService patentsService;

  @GetMapping("/patents")
  public String groupMissionStatementPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Patents");
    model.addAttribute("path", "/publications/patents");
    model.addAttribute("content", "patentsFragment");
    model.addAttribute("contentHead", "patentsFragmentHead");
    model.addAttribute("bannerBundle", "Publications");
    model.addAttribute("banner", "Patents");

    model.addAttribute("list", patentsService.getAll());

    return "/basic/layout";
  }

  @PostMapping("/patents/add")
  public String patentsAddPost(@RequestParam Map<String, String> data) {
    Patents patents = new Patents(data.get("content"));
    patents.setIsRegist(Integer.parseInt(data.get("isRegist")));
    patents.setRegistNumber(data.get("registNumber"));
    if (data.get("registDate") != "") {
      patents.setRegistDate(Date.valueOf(data.get("registDate")));
    }
    patentsService.add(patents);

    return "redirect:/patents";
  }
}
