package com.kozlovskaya.springdata.lesson7.repositories;

import com.kozlovskaya.springdata.lesson7.data.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImplWeb implements ProductDaoWeb {
    private final SessionFactoryUtilsWeb sessionFactoryUtilsWeb;


    public ProductDaoImplWeb(SessionFactoryUtilsWeb sessionFactoryUtilsWeb) {
        this.sessionFactoryUtilsWeb = sessionFactoryUtilsWeb;
    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactoryUtilsWeb.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactoryUtilsWeb.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public void saveOrUpdate(Product product) {
        try (Session session = sessionFactoryUtilsWeb.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactoryUtilsWeb.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

}
