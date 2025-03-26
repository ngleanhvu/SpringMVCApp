package com.ngleanhvu.springmvcapp.repository.impl;

import com.ngleanhvu.springmvcapp.pojo.Cart;
import com.ngleanhvu.springmvcapp.pojo.OrderDetail;
import com.ngleanhvu.springmvcapp.pojo.SaleOrder;
import com.ngleanhvu.springmvcapp.pojo.User;
import com.ngleanhvu.springmvcapp.repository.ProductRepository;
import com.ngleanhvu.springmvcapp.repository.ReceiptRepository;
import com.ngleanhvu.springmvcapp.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Transient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.Authenticator;
import java.util.Date;
import java.util.Map;

@Repository
public class ReceiptRepositoryImpl implements ReceiptRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<String, Cart> carts) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUserByUsername(authentication.getName());
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setCreatedDate(new Date());
        saleOrder.setUser(user);
        session.persist(saleOrder);

        for (Cart cart : carts.values()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(saleOrder);
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setUnitPrice(cart.getUnitPrice());
            orderDetail.setProduct(this.productRepository.getProductById(cart.getId()));
            session.persist(orderDetail);
        }
        return true;
    }
}
