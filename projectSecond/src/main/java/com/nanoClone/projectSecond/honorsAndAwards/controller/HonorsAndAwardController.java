package com.nanoClone.projectSecond.honorsAndAwards.controller;

import java.sql.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nanoClone.projectSecond.honorsAndAwards.domain.HonorsAndAwards;
import com.nanoClone.projectSecond.honorsAndAwards.service.HonorsAndAwardsService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HonorsAndAwardController {
  @Autowired
  HonorsAndAwardsService honorsAndAwardsService;

  @PostMapping("/honorsAndAwardsAdd")
  public String add(@RequestParam Map<String, String> data, HttpSession session,
      RedirectAttributes redirectAttributes) {
    Date tempDate = Date.valueOf(data.get("awardDate"));
    HonorsAndAwards tempAward =
        new HonorsAndAwards(Integer.parseInt(data.get("memberId")), data.get("title"), tempDate);
    tempAward.setContents(data.get("content"));

    honorsAndAwardsService.add(tempAward);

    return "redirect:/professor";
  }

}
