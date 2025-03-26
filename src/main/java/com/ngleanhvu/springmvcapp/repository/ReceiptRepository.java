package com.ngleanhvu.springmvcapp.repository;

import com.ngleanhvu.springmvcapp.pojo.Cart;

import java.util.Map;

public interface ReceiptRepository {
    boolean addReceipt(Map<String, Cart> carts);
}
