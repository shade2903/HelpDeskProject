package com.project.haiduk.repository.impl;

import com.project.haiduk.domain.User;
import com.project.haiduk.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final String GET_BY_EMAIL = "from User u where u.email = :email";

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    @Override
    public User getByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_BY_EMAIL);
        query.setParameter("email", email);
        return (User) query.uniqueResult();
    }

    @Override
    public List<User> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }
}
