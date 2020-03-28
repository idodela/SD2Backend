package com.sd2backend.backendsd2.repositories.interfaces;

import com.sd2backend.backendsd2.models.User;

import java.util.List;

public interface UserInterface {

    List<User> findAllUsers();

    User findById(String id);


    User save(User user);

    void delete(User user);
}
