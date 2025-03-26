package com.ngleanhvu.springmvcapp.controller.rest;
import com.ngleanhvu.springmvcapp.pojo.Product;
import com.ngleanhvu.springmvcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ApiProductController {
    @Autowired
    private ProductService productService;

    @DeleteMapping("/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") int id) {
        this.productService.deleteProductById(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(this.productService.getProducts(params));
    }
}
