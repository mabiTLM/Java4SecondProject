package com.nanoClone.projectSecond.newsBoard.controller;

import java.io.File;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nanoClone.projectSecond.newsBoard.domain.NewsBoard;
import com.nanoClone.projectSecond.newsBoard.service.NewsBoardService;
import jakarta.servlet.http.HttpSession;

@Controller
public class NewsBoardController {

  @Autowired
  NewsBoardService newsBoardService;

  @GetMapping("/news")
  public String newsPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "News");
    model.addAttribute("path", "/news/index");
    model.addAttribute("content", "newsFragment");
    model.addAttribute("contentHead", "newsFragmentHead");
    model.addAttribute("bannerBundle", "News");
    model.addAttribute("banner", "News");
    return "/basic/layout";
  }

  @GetMapping("/news/add")
  public String newsAddPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "News");
    model.addAttribute("path", "/news/addNews");
    model.addAttribute("content", "addNewsFragment");
    model.addAttribute("contentHead", "addNewsFragmentHead");
    return "/basic/layout";
  }

  @PostMapping("/news/add")
  public String add(@RequestParam Map<String, String> data, HttpSession session,
      RedirectAttributes redirectAttributes) {
    try {
      String tempContent = data.get("content");
      tempContent = tempContent.replaceAll("width=\"[0-9]*\"", "width=\"100%\"");
      tempContent = tempContent.replaceAll("height=\"[0-9]*\"", "height=\"auto\"");
      newsBoardService.add(new NewsBoard(data.get("category"), data.get("title"), tempContent));

    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("requestError", "게시글을 정확히 입력해주세요");
      e.printStackTrace();
    }
    return "redirect:/";
  }

  @PostMapping("/upload")
  @ResponseBody
  public ModelMap uploadImage(MultipartHttpServletRequest req) {
    ModelMap model = new ModelMap();
    try {
      MultipartFile uploadFile = req.getFile("upload");
      System.out.println(uploadFile.getOriginalFilename());
      String originName = uploadFile.getOriginalFilename();
      String[] tempNames = originName.split("[.]");
      System.out.println(tempNames[0]);
      String ext = originName.substring(originName.indexOf("."));
      String randomName = UUID.randomUUID() + ext;
      String savePath =
          "C:\\Users\\KGA\\git\\Java4SecondProject\\projectSecond\\src\\main\\resources\\static\\images\\upload\\"
              + randomName;
      String uploadUrl = "/images/upload/" + randomName;
      File file = new File(savePath);
      uploadFile.transferTo(file);
      model.addAttribute("uploaded", true);
      model.addAttribute("url", uploadUrl);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return model;
  }
}
