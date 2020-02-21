package com.chao.weblog.controller;

import com.chao.weblog.pojo.Tag;
import com.chao.weblog.service.TagService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;


@RequestMapping("/admin/tag")
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/listTag")
    public String listTag(@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/editOrSaveTag")
    public String toEditOrSaveTagPage(Integer id, Model model) {
        Tag tag = new Tag();
        if (id != null) {
            tag = tagService.getTag(id);
        }
        model.addAttribute("tag", tag);
        return "admin/tags-input";
    }

    @PostMapping("/editOrSaveTag")
    public String editOrSaveTag(@Valid Tag tag, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()||tagService.findTagByName(tag.getName())!=null){
            result.rejectValue("name","nameError","操作失败，已经存在该名字！");
            return "admin/tags-input";
        }
        Tag t = tagService.editOrSaveTag(tag);
        if (t != null) {
            redirectAttributes.addFlashAttribute("message", "操作成功！");
        } else {
            redirectAttributes.addFlashAttribute("message", "操作失败！");
        }
        return "redirect:/admin/tag/listTag";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message", "操作成功！");
        return "redirect:/admin/tag/listTag";
    }

}
