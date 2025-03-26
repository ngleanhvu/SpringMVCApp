package com.ngleanhvu.springmvcapp.repository.impl;

import com.ngleanhvu.springmvcapp.pojo.Product;
import com.ngleanhvu.springmvcapp.repository.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
@PropertySource("classpath:config.properties")
public class ProductRepositoryImpl implements ProductRepository {
    private static final int PAGE_SIZE = 6;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment environment;

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = b.createQuery(Product.class);
        Root<Product> root = q.from(Product.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }

            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                predicates.add(b.greaterThanOrEqualTo(root.get("price"), fromPrice));
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                predicates.add(b.lessThanOrEqualTo(root.get("price"), toPrice));
            }

            String cateId = params.get("categoryId");
            if (cateId != null && !cateId.isEmpty()) {
                predicates.add(b.equal(root.get("category").as(Integer.class), cateId));
            }

            q.where(predicates.toArray(Predicate[]::new));

            String orderBy = params.get("orderBy");
            if (orderBy != null && !orderBy.isEmpty()) {
                q.orderBy(b.asc(root.get(orderBy)));
            }
        }

        Query<Product> query = s.createQuery(q);

        if (params != null) {
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int pageSize = Integer.parseInt(this.environment.getProperty("PAGE_SIZE", "10"));
            int start = (page - 1) * pageSize;

            query.setMaxResults(pageSize);
            query.setFirstResult(start);
        }

        return query.getResultList();

    }

    @Override
    public Product saveOrUpdate(Product p) {
        Session s = Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
        if (p.getId() == null)
            s.persist(p);
        else
            s.merge(p);
        return p;
    }

    @Override
    public Product getProductById(int id) {
        Session s = Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
        return s.get(Product.class, id);
    }

    @Override
    public int countProducts() {
        Session s = this.factory.getObject().getCurrentSession();
        Query query = s.createQuery("select count(*) from Product");
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public boolean deleteProductById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Product product = this.getProductById(id);
        s.delete(product);
        return true;
    }
}
