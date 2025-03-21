package com.ngleanhvu.springmvcapp.controller;


import com.ngleanhvu.springmvcapp.service.CategoryService;
import com.ngleanhvu.springmvcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nguoideptrangian
 */
@Controller
@ControllerAdvice
public class IndexController {
    @Autowired
    private CategoryService cateSer;
    @Autowired
    private ProductService proSer;

    @ModelAttribute
    public void commonResponse(Model model) {
        model.addAttribute("categories", this.cateSer.getCates());
    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {

        model.addAttribute("products", this.proSer.getProducts(params));
        return "index";
    }

}
