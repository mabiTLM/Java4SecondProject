package com.nanoClone.projectSecond.links.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nanoClone.projectSecond.links.domain.Links;
import com.nanoClone.projectSecond.links.service.LinksService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LinksController {
  @Autowired
  LinksService linksService;

  @GetMapping("/links")
  public String linksPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Links");
    model.addAttribute("path", "/links/links");
    model.addAttribute("content", "linksFragment");
    model.addAttribute("contentHead", "linksFragmentHead");
    model.addAttribute("bannerBundle", "Links");
    model.addAttribute("banner", "Links");
    model.addAttribute("list", linksService.getLinks());
    return "/basic/layout";
  }

  @GetMapping("/links/add")
  public String linksAddPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Links");
    model.addAttribute("path", "/links/linksAdd");
    model.addAttribute("content", "linksAddFragment");
    model.addAttribute("contentHead", "linksAddFragmentHead");
    model.addAttribute("bannerBundle", "Links");
    model.addAttribute("banner", "Links");
    return "/basic/layout";
  }

  @PostMapping("/links/add")
  public String linksAddPagePost(@RequestParam Map<String, String> data, HttpSession session,
      RedirectAttributes redirectAttributes) {
    linksService.add(new Links(data.get("category"), data.get("image"), data.get("link")));
    return "redirect:/links";
  }

  @GetMapping("/funding_sources")
  public String fundingSourcesPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Funding sources");
    model.addAttribute("path", "/links/fundingSources");
    model.addAttribute("content", "fundingSourcesFragment");
    model.addAttribute("contentHead", "fundingSourcesFragmentHead");
    model.addAttribute("bannerBundle", "Links");
    model.addAttribute("banner", "Funding sources");
    model.addAttribute("list", linksService.getFunding());
    return "/basic/layout";
  }

}
