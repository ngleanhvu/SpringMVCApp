package com.ngleanhvu.springmvcapp.controller;

import com.ngleanhvu.springmvcapp.pojo.Product;
import com.ngleanhvu.springmvcapp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    private ProductService prodSer;

    @GetMapping("/products")
    public String manageProduct(Model model) {
        model.addAttribute("product", new Product());
        return "products";
    }

    @GetMapping("/products/{productId}")
    public String updateProduct(@PathVariable("productId") int productId,
                                Model model) {
        model.addAttribute("product", this.prodSer.getProductById(productId));
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(@Valid @ModelAttribute(value = "product") Product p,
                             BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if(this.prodSer.saveOrUpdate(p) != null) {
                return "redirect:/";
            }
        }
        return "products";
    }


}