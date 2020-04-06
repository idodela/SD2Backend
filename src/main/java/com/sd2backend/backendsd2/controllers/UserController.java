package com.sd2backend.backendsd2.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sd2backend.backendsd2.models.User;
import com.sd2backend.backendsd2.models.UserType;
import com.sd2backend.backendsd2.models.helper.AppconfigJ;
import com.sd2backend.backendsd2.models.helper.AuthenticationException;
import com.sd2backend.backendsd2.repositories.UserRepository;
import com.sd2backend.backendsd2.security.JWToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AppconfigJ appconfigJ;

    @GetMapping("")
    public List<User> getUsers() {
        System.out.println(userRepository.findAllUsers());
        return userRepository.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") String id){
        User user= userRepository.findById(id);
        if(user== null){
            throw new RuntimeException("User with id:" + id + "not found");
        }
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<User> authenticateUser(@RequestBody ObjectNode signUpInfo) throws AuthenticationException {
        String password = signUpInfo.get("password") == null ? null : signUpInfo.get("password").asText();
        String id = signUpInfo.get("id") == null ? null : signUpInfo.get("id").asText();
        User user = userRepository.findById(id);

        if (user == null) throw new AuthenticationException("Invalid user and/or password");
        if (!user.validateEncodedPassword(password)) throw new AuthenticationException("Invalid user and/or password");

        JWToken jwToken = new JWToken(user.getName(), user.getId(), user.getUserType().toString());
        // Issue a token for the user valid for some time
        String tokenString = jwToken.encode(appconfigJ.passphrase, appconfigJ.expiration, appconfigJ.issuer);

        return ResponseEntity.accepted()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                .body(user);
    }


    @PostMapping("")
    public ResponseEntity<Object> createUser(@RequestBody ObjectNode signupInfo) {

        String id = signupInfo.get("id") == null ? null : signupInfo.get("id").asText();
        String password = signupInfo.get("password") == null ? null : signupInfo.get("password").asText();
        String name = signupInfo.get("name") == null ? null : signupInfo.get("name").asText();
        String surname = signupInfo.get("surname") == null ? null : signupInfo.get("surname").asText();
        String email = signupInfo.get("email") == null ? null : signupInfo.get("email").asText();
        String role = signupInfo.get("userType") == null ? null : signupInfo.get("userType").asText();

        User user = new User();

        assert role != null;
        UserType userType = UserType.valueOf(role.toUpperCase());

        user.setId(id);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setUserType(userType);


        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public  String  deleteUser(@PathVariable String id){
        User user = this.userRepository.findById(id);
        this.userRepository.delete(user);

        return "Delete successful";


    }


}
