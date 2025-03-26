package com.ngleanhvu.springmvcapp.repository.impl;

import com.ngleanhvu.springmvcapp.pojo.User;
import com.ngleanhvu.springmvcapp.repository.UserRepository;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public User getUserByUsername(String username) {
        Session session = (Session) sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("from User where username=:username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }
}
