package com.nanoClone.projectSecond.galleryBoard.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nanoClone.projectSecond.galleryBoard.domain.GalleryBoard;
import com.nanoClone.projectSecond.galleryBoard.service.GalleryBoardService;
import jakarta.servlet.http.HttpSession;

@Controller
public class GalleryBoardController {

  @Autowired
  GalleryBoardService galleryBoardService;

  int count = 8;

  @GetMapping("/galleryBoard")
  public String galleryBoardPage(Model model, @RequestParam Map<String, String> data) {
    int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
    model.addAttribute("title", "Gallery");
    model.addAttribute("path", "/gallery/index");
    model.addAttribute("content", "galleryFragment");
    model.addAttribute("contentHead", "galleryFragmentHead");
    model.addAttribute("bannerBundle", "Gallery");
    model.addAttribute("banner", "Gallery");

    model.addAttribute("list", galleryBoardService.getAll(page, count));
    model.addAttribute("pageCount", galleryBoardService.getPageCount(count));
    return "/basic/layout";
  }

  @GetMapping("/galleryBoard/search")
  public String galleryBoardSearchPage(Model model, @RequestParam Map<String, String> data,
      HttpSession session) {
    int page = data.get("page") != null ? Integer.parseInt(data.get("page")) : 1;
    model.addAttribute("title", "Gallery");
    model.addAttribute("path", "/gallery/search");
    model.addAttribute("content", "gallerySearchFragment");
    model.addAttribute("contentHead", "gallerySearchFragmentHead");
    model.addAttribute("bannerBundle", "Gallery");
    model.addAttribute("banner", "Gallery");

    String tempSearch = "";
    if (data.get("search") != null) {
      tempSearch = data.get("search");
      session.setAttribute("galleryBoardSearch", data.get("search"));
    } else if (session.getAttribute("galleryBoardSearch") != null) {
      tempSearch = session.getAttribute("galleryBoardSearch").toString();
    }

    model.addAttribute("list", galleryBoardService.getAll(tempSearch, page, count));
    model.addAttribute("pageCount", galleryBoardService.getPageCount(tempSearch, count));
    return "/basic/layout";
  }

  @GetMapping("/galleryBoard/add")
  public String galleryBoardAddPage(Model model, @RequestParam Map<String, String> data) {
    model.addAttribute("title", "Gallery");
    model.addAttribute("path", "/gallery/galleryAdd");
    model.addAttribute("content", "galleryAddFragment");
    model.addAttribute("contentHead", "galleryAddFragmentHead");
    return "/basic/layout";
  }

  @PostMapping("/galleryBoard/add")
  public String galleryBoardAddPagePost(@RequestParam Map<String, String> data, HttpSession session,
      RedirectAttributes redirectAttributes) {
    try {
      if (data.get("title") != "") {
        GalleryBoard tempGalleryBoard =
            new GalleryBoard(Integer.parseInt(data.get("memberId")), data.get("title"));
        String tempContent = data.get("content");
        tempContent = tempContent.replaceAll("width=\"[0-9]*\"", "width=\"100%\"");
        tempContent = tempContent.replaceAll("height=\"[0-9]*\"", "height=\"auto\"");
        tempContent = tempContent.replace("\n", "<br />");
        tempGalleryBoard.setContent(tempContent);
        galleryBoardService.add(tempGalleryBoard);
      }
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("requestError", "게시글을 정확히 입력해주세요");
      e.printStackTrace();
    }
    return "redirect:/galleryBoard";
  }

  @GetMapping("/galleryBoard/{galleryBoardId}")
  public String itemPage(Model model, @PathVariable("galleryBoardId") int boardId) {
    galleryBoardService.upViews(boardId);
    GalleryBoard galleryBoard = galleryBoardService.get(boardId);

    model.addAttribute("title", "Gallery");
    model.addAttribute("path", "/gallery/item");
    model.addAttribute("content", "galleryItemFragment");
    model.addAttribute("contentHead", "galleryItemFragmentHead");
    model.addAttribute("bannerBundle", "Gallery");
    model.addAttribute("banner", "Gallery");

    model.addAttribute("galleryBoard", galleryBoard);

    try {
      GalleryBoard previousBoard = galleryBoardService.get(boardId - 1);
      model.addAttribute("previousItem", previousBoard);
    } catch (Exception e) {
      System.out.println("최초글");
    }
    try {
      GalleryBoard nextBoard = galleryBoardService.get(boardId + 1);
      model.addAttribute("nextItem", nextBoard);
    } catch (Exception e) {
      System.out.println("마지막글");
    }
    return "/basic/layout";
  }

  @PostMapping("/galleryBoard/delete")
  public String galleryBoardDeletePagePost(@RequestParam Map<String, String> data,
      HttpSession session, RedirectAttributes redirectAttributes) {
    galleryBoardService.delete(Integer.parseInt(data.get("id")));
    return "redirect:/galleryBoard";
  }
}
