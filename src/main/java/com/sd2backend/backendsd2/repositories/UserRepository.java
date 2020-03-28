package com.sd2backend.backendsd2.repositories;

import com.sd2backend.backendsd2.models.User;
import com.sd2backend.backendsd2.repositories.interfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository implements UserInterface {

    @Autowired
    private EntityManager em ;


    @Override
    public List<User> findAllUsers() {
        TypedQuery<User> query= em.createQuery("select u from User u", User.class);
        return query.getResultList();    }

    @Override
    public User findById(String id) {
        return em.find(User.class,id );
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) em.persist(user);
        else em.merge(user);

        return user;
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }
}
