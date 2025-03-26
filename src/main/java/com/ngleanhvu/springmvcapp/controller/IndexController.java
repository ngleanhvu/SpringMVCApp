package com.ngleanhvu.springmvcapp.controller;


import com.ngleanhvu.springmvcapp.service.CategoryService;
import com.ngleanhvu.springmvcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

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
@PropertySource("classpath:config.properties")
public class IndexController {
    @Autowired
    private CategoryService cateSer;
    @Autowired
    private ProductService proSer;
    @Autowired
    private Environment environment;

    @ModelAttribute
    public void commonResponse(Model model, Principal principal) {
        model.addAttribute("categories", this.cateSer.getCates());
        if(principal != null) {
            model.addAttribute("user", principal.getName());
        }
    }

    @GetMapping
    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("products", this.proSer.getProducts(params));
        int pageSize = Integer.parseInt(Objects.requireNonNull(environment.getProperty("PAGE_SIZE")));
        int countProduct = this.proSer.countProducts();
        model.addAttribute("counter", Math.ceil((double) countProduct / (double) pageSize));

        return "index";
    }

}
