package com.sd2backend.backendsd2.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "User")
@NamedQueries({
        @NamedQuery(name="find_all_users", query = "select u from User u ")
})
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "type")
      @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String id, String name, String surname, String email, UserType userType, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userType = userType;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean validateEncodedPassword(String password) {
        return this.password.equals(password);
    }
}
