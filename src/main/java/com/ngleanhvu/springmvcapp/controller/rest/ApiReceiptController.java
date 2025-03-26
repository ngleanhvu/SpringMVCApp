package com.ngleanhvu.springmvcapp.controller.rest;

import com.ngleanhvu.springmvcapp.pojo.Cart;
import com.ngleanhvu.springmvcapp.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/receipts")
@CrossOrigin
public class ApiReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public void addReceipts(@RequestBody Map<String, Cart> carts) {
        this.receiptService.addReceipt(carts);
    }
}
