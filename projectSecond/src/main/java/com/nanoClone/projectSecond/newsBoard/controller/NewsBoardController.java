package com.nanoClone.projectSecond.newsBoard.controller;

import java.io.File;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  int count = 10;

  @GetMapping("/news")
  public String newsPage(Model model, @RequestParam Map<String, String> data) {
    int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
    model.addAttribute("title", "News");
    model.addAttribute("path", "/news/index");
    model.addAttribute("content", "newsFragment");
    model.addAttribute("contentHead", "newsFragmentHead");
    model.addAttribute("bannerBundle", "News");
    model.addAttribute("banner", "News");
    model.addAttribute("list", newsBoardService.getAll(page, count));
    model.addAttribute("pageCount", newsBoardService.getPageCount(count));
    return "/basic/layout";
  }

  @GetMapping("/news/{newsBoardId}")
  public String itemPage(Model model, @PathVariable("newsBoardId") int boardId) {
    newsBoardService.upViews(boardId);
    NewsBoard newsboard = newsBoardService.get(boardId);
    model.addAttribute("title", "News");
    model.addAttribute("bannerBundle", "News");
    model.addAttribute("banner", "News");
    model.addAttribute("path", "/news/item");
    model.addAttribute("content", "newsItemFragment");
    model.addAttribute("contentHead", "newsItemFragmentHead");
    newsboard.setContent(newsboard.getContent().replace("\n", "<br />"));
    model.addAttribute("newsBoard", newsboard);

    try {
      NewsBoard previousBoard = newsBoardService.get(boardId - 1);
      model.addAttribute("previousItem", previousBoard);
    } catch (Exception e) {
      System.out.println("최초글");
    }
    try {
      NewsBoard nextBoard = newsBoardService.get(boardId + 1);
      model.addAttribute("nextItem", nextBoard);
    } catch (Exception e) {
      System.out.println("마지막글");
    }
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
    return "redirect:/news";
  }

  @GetMapping("/news/edit")
  public String newsEditPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "News");
    model.addAttribute("path", "/news/editNews");
    model.addAttribute("content", "editNewsFragment");
    model.addAttribute("contentHead", "editNewsFragmentHead");
    model.addAttribute("editPost", newsBoardService.get(Integer.parseInt(data.get("currentPost"))));
    return "/basic/layout";
  }

  @PostMapping("/news/edit")
  public String editPost(@RequestParam Map<String, String> data, HttpSession session,
      RedirectAttributes redirectAttributes) {
    try {
      String tempContent = data.get("content");
      tempContent = tempContent.replaceAll("width=\"[0-9]*\"", "width=\"100%\"");
      tempContent = tempContent.replaceAll("height=\"[0-9]*\"", "height=\"auto\"");
      NewsBoard editBoard = new NewsBoard(data.get("category"), data.get("title"), tempContent);
      editBoard.setId(Integer.valueOf(data.get("id")));
      newsBoardService.edit(editBoard);

    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("requestError", "게시글을 정확히 입력해주세요");
      e.printStackTrace();
    }
    return "redirect:/news";
  }

  @PostMapping("/news/delete")
  public String delete(@RequestParam Map<String, String> data, HttpSession session,
      RedirectAttributes redirectAttributes) {
    try {
      newsBoardService.delete(Integer.parseInt(data.get("currentPost")));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/news";
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
