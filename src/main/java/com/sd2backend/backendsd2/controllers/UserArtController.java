package com.sd2backend.backendsd2.controllers;

import com.sd2backend.backendsd2.models.Art;
import com.sd2backend.backendsd2.models.User;
import com.sd2backend.backendsd2.models.helper.AppconfigJ;
import com.sd2backend.backendsd2.repositories.UserArtRepository;
import com.sd2backend.backendsd2.repositories.UserRepository;
import com.sd2backend.backendsd2.security.JWToken;
import com.sd2backend.backendsd2.security.JWTokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userArts")
public class UserArtController {

    @Autowired
    private UserArtRepository userArtRepository;

    @Autowired
    AppconfigJ appconfigJ;

    private String getUserIdFromToken(String token) {
        String s = token.replace("Bearer ", "");
        JWTokenInfo jwTokenInfo = JWToken.decode(s, appconfigJ.passphrase);
        return jwTokenInfo.getId();
    }

    @GetMapping("")
    public List<Art> getUserArts(@RequestHeader(name="Authorization") String token){
       return  this.userArtRepository.findArtsByUser(getUserIdFromToken(token));
    }

}
