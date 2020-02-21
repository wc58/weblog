package com.chao.weblog.controller;

import com.chao.weblog.pojo.Type;
import com.chao.weblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/listType")
    public String listType(@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/editOrSave")
    public String toEditOrSavePage(Integer id, Model model) {
        Type type = new Type();
        if (id != null) {
            type = typeService.getType(id);
        }
        model.addAttribute("type", type);
        return "admin/types-input";
    }

    @PostMapping("/editOrSave")
    public String editOrSave(@Valid Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || typeService.getTypeByName(type.getName()) != null) {
            bindingResult.rejectValue("name","nameError","操作失败，已经存在该名字！");
            return "admin/types-input";
        }
        Type t = typeService.editOrSaveType(type);
        if (t != null) {
            redirectAttributes.addFlashAttribute("message", "操作成功！");
        } else {
            redirectAttributes.addFlashAttribute("message", "操作失败！");

        }
        return "redirect:/admin/type/listType";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message", "操作成功！");
        return "redirect:/admin/type/listType";
    }


}
