package com.sd2backend.backendsd2.repositories;

import com.sd2backend.backendsd2.models.Art;
import com.sd2backend.backendsd2.models.User;
import com.sd2backend.backendsd2.repositories.interfaces.ArtInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Repository
@Transactional
public class ArtRepository implements  ArtInterface {

    @Autowired
    private EntityManager em;

    @Override
    public Art save(Art art, String userId) {
        User user = em.find(User.class, userId);

        art.setUser_id(user);

         em.merge(art);



        return art;
    }

    @Override
    public List<Art> findAll() {
        TypedQuery<Art> namedQuery = em.createNamedQuery("find_all_arts", Art.class);

        return  namedQuery.getResultList();
    }

    @Override
    public List<Art>findArtsByUser(String userId) {
        User user = em.find(User.class, userId);
        TypedQuery<Art> query = em.createQuery("SELECT a  FROM Art a WHERE a.User_id = :user", Art.class);
        query.setParameter("user", user);

        return query.getResultList();
    }

    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ignored) {
        }
        return outputStream.toByteArray();
    }
}
