package com.nanoClone.projectSecond.link.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LinkController {

  @GetMapping("/links")
  public String journalsPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Links");
    model.addAttribute("path", "/links/links");
    model.addAttribute("content", "linksFragment");
    model.addAttribute("contentHead", "linksFragmentHead");
    model.addAttribute("bannerBundle", "Links");
    model.addAttribute("banner", "Links");
    return "/basic/layout";
  }

}
