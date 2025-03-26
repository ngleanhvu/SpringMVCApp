package com.ngleanhvu.springmvcapp.service.impl;

import com.ngleanhvu.springmvcapp.pojo.Cart;
import com.ngleanhvu.springmvcapp.repository.ReceiptRepository;
import com.ngleanhvu.springmvcapp.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;


    @Override
    public boolean addReceipt(Map<String, Cart> carts) {
        return this.receiptRepository.addReceipt(carts);
    }
}
