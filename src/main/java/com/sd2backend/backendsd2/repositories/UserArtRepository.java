package com.sd2backend.backendsd2.repositories;

import com.sd2backend.backendsd2.models.Art;
import com.sd2backend.backendsd2.models.User;
import com.sd2backend.backendsd2.repositories.interfaces.UserArtInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserArtRepository implements UserArtInterface {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Art> findArtsByUser(String userId) {
        User user = entityManager.find(User.class, userId);
        TypedQuery<Art> query = entityManager.createQuery("SELECT art_id FROM UserArt ua WHERE user_id = :user", Art.class);
        query.setParameter("user", user);

        return query.getResultList();

    }
}
