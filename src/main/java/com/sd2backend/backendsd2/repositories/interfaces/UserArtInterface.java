package com.sd2backend.backendsd2.repositories.interfaces;

import com.sd2backend.backendsd2.models.Art;

import java.util.List;

public interface UserArtInterface {

    List<Art> findArtsByUser(String userId);
}
