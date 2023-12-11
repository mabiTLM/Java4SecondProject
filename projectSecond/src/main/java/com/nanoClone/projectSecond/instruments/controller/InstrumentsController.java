package com.nanoClone.projectSecond.instruments.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nanoClone.projectSecond.instruments.domain.Instruments;
import com.nanoClone.projectSecond.instruments.service.InstrumentsService;

@Controller
public class InstrumentsController {
  @Autowired
  InstrumentsService instrumentsService;

  int count = 8;

  @GetMapping("/instruments")
  public String instrumentsPage(Model model, @RequestParam Map<String, String> data) {
    int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
    model.addAttribute("title", "Instruments");
    model.addAttribute("path", "/researches/instruments");
    model.addAttribute("content", "instrumentsFragment");
    model.addAttribute("contentHead", "instrumentsFragmentHead");
    model.addAttribute("bannerBundle", "Researches");
    model.addAttribute("banner", "Instruments");
    model.addAttribute("list", instrumentsService.getAll(page, count));
    model.addAttribute("pageCount", instrumentsService.getPageCount(count));
    return "/basic/layout";
  }

  @GetMapping("/instruments/add")
  public String instrumentsAddPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Instruments");
    model.addAttribute("path", "/researches/instrumentsAdd");
    model.addAttribute("content", "instrumentsAddFragment");
    model.addAttribute("contentHead", "instrumentsAddFragmentHead");
    model.addAttribute("bannerBundle", "Researches");
    model.addAttribute("banner", "Instruments");
    return "/basic/layout";
  }

  @PostMapping("/instruments/add")
  public String instrumentsAddPagePost(Model model, @RequestParam Map<String, String> data) {
    Instruments instruments = new Instruments(data.get("title"), data.get("image"));

    instrumentsService.add(instruments);
    return "redirect:/instruments";
  }
}
