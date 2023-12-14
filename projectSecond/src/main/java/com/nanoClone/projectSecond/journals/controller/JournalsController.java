package com.nanoClone.projectSecond.journals.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.nanoClone.projectSecond.journals.domain.Journals;
import com.nanoClone.projectSecond.journals.service.JournalsService;

@Controller
public class JournalsController {
  @Autowired
  JournalsService journalsService;

  @GetMapping("/journals")
  public String journalsPage(Model model, @RequestParam Map<String, String> data) {
    int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
    model.addAttribute("page", page);
    model.addAttribute("title", "Journals");
    model.addAttribute("path", "/publications/journals");
    model.addAttribute("content", "journalsFragment");
    model.addAttribute("contentHead", "journalsFragmentHead");
    model.addAttribute("bannerBundle", "Publications");
    model.addAttribute("banner", "Publications");
    return "/basic/layout";
  }

  @GetMapping("/journals/add")
  public String journalsAddPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Journals");
    model.addAttribute("path", "/publications/journalsAdd");
    model.addAttribute("content", "journalsAddFragment");
    model.addAttribute("contentHead", "journalsAddFragmentHead");

    return "/basic/layout";
  }

  @PostMapping("/journals/add")
  public String journalsAddPagePost(Model model, @RequestParam Map<String, String> data,
      @RequestParam("file") MultipartFile file, @RequestParam("image") MultipartFile image) {
    Journals tempJournals =
        new Journals(Integer.parseInt(data.get("selected")), Date.valueOf(data.get("makeDate")),
            data.get("title"), data.get("maker"), data.get("bookName"));
    tempJournals.setVolume(data.get("volume"));
    tempJournals.setPage(data.get("page"));
    tempJournals.setImpactFactor(data.get("impactFactor"));
    tempJournals.setLink(data.get("link"));


    String originName = file.getOriginalFilename();
    String[] tempNames = originName.split("[.]");
    String ext = originName.substring(originName.indexOf("."));
    String randomName = UUID.randomUUID() + ext;
    String savePath = System.getProperty("user.dir")
        + "\\src\\main\\resources\\static\\images\\journals\\upload\\" + randomName;
    String uploadUrl = "/images/journals/upload/" + randomName;

    File saveFile = new File(savePath);
    try {
      file.transferTo(saveFile);
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    tempJournals.setFile(uploadUrl);

    String originImageName = image.getOriginalFilename();
    String[] tempImageNames = originImageName.split("[.]");
    String extImage = originImageName.substring(originImageName.indexOf("."));
    String randomImageName = UUID.randomUUID() + extImage;
    String saveImagePath = System.getProperty("user.dir")
        + "\\src\\main\\resources\\static\\images\\journals\\upload\\" + randomImageName;
    String uploadImageUrl = "/images/journals/upload/" + randomImageName;
    File saveImageFile = new File(saveImagePath);
    try {
      image.transferTo(saveImageFile);
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    tempJournals.setImage(uploadImageUrl);


    journalsService.add(tempJournals);

    return "redirect:/journals";
  }

}
