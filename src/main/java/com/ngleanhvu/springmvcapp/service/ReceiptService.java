package com.ngleanhvu.springmvcapp.service;

import com.ngleanhvu.springmvcapp.pojo.Cart;

import java.util.Map;

public interface ReceiptService {
    boolean addReceipt(Map<String, Cart> carts);
}
