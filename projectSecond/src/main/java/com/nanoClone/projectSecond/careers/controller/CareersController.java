package com.nanoClone.projectSecond.careers.controller;

import java.sql.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nanoClone.projectSecond.careers.domain.Careers;
import com.nanoClone.projectSecond.careers.service.CareersService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CareersController {
  @Autowired
  CareersService careersService;

  @PostMapping("/careerAdd")
  public String add(@RequestParam Map<String, String> data, HttpSession session,
      RedirectAttributes redirectAttributes) {
    try {
      Careers tempCareer = new Careers(Integer.parseInt(data.get("memberId")), data.get("title"));
      tempCareer.setUniversity(Integer.parseInt(data.get("university")));
      tempCareer.setContent(data.get("content"));
      if (data.get("start") != "") {
        tempCareer.setStart(monthToDate(data.get("start")));
      }
      if (data.get("end") != "") {
        tempCareer.setEnd(monthToDate(data.get("end")));
      }
      careersService.add(tempCareer);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/professor";
  }

  private Date monthToDate(String date) {
    date = date + "-01";
    return Date.valueOf(date);
  }
}
