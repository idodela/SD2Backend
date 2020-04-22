package com.sd2backend.backendsd2.repositories.interfaces;

import com.sd2backend.backendsd2.models.Art;

import java.util.List;

public interface ArtInterface {
    Art save(Art art, String userId);

    List<Art> findAll ();
}
