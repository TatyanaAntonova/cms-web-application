package com.example.cmswebapp.controllers;

import com.example.cmswebapp.dao.PageDAO;
import com.example.cmswebapp.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {
    private PageDAO pageDAO;

    @Autowired
    public MainController(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    @GetMapping()
    public String welcomePage(Model model) {

        model.addAttribute("pages", pageDAO.getPublishedList());
        return "views/welcome";
    }

    @GetMapping("/unpublished")
    public String viewUnpublishedPageList(Model model) {

        model.addAttribute("pages", pageDAO.getUnpublishedList());
        return "views/unpublished";
    }

    @GetMapping("/{slug}")
    public String viewPage(@PathVariable("slug") String slug, Model model) {
        model.addAttribute("page", pageDAO.get(slug));
        return "views/page";
    }

    @GetMapping("/new")
    public String newPage(@ModelAttribute("page") Page page) {
        return "views/new";
    }

    @PostMapping("/new-page")
    public String create(@ModelAttribute("page") @Valid Page page,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "views/new";
        }

        pageDAO.create(page);
        return "redirect:";
    }

    @GetMapping("/{slug}/edit")
    public String edit(Model model, @PathVariable("slug") String slug) {
        model.addAttribute("page", pageDAO.get(slug));
        return "views/edit";
    }

    @PatchMapping("/{slug}")
    public String update(@ModelAttribute("page") @Valid Page page, BindingResult bindingResult, @PathVariable("slug") String slug) {
        if (bindingResult.hasErrors()) {
            return "views/edit";
        }

        pageDAO.update(slug, page);
        return "redirect:";
    }

    @DeleteMapping("/{slug}")
    public String delete(@PathVariable("slug") String slug) {
        pageDAO.delete(slug);
        return "redirect:";
    }
}