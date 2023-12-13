package com.nanoClone.projectSecond.sitemap.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SitemapController {
  @GetMapping("/sitemap")
  public String sitemapPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Sitemap");
    model.addAttribute("path", "/sitemap/index");
    model.addAttribute("content", "sitemapFragment");
    model.addAttribute("contentHead", "sitemapFragmentHead");
    model.addAttribute("bannerBundle", "Sitemap");
    model.addAttribute("banner", "Sitemap");

    return "/basic/layout";
  }
}
