package com.sd2backend.backendsd2.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "Art")
@NamedQueries({
        @NamedQuery(name = "find_all_arts", query = "SELECT a FROM Art a"),
        @NamedQuery(name = "find_all_arts_by_user", query = "SELECT a  FROM Art a WHERE a.User_id = :user"),
        @NamedQuery(name = "find_loaned_arts_by_user", query = "SELECT a FROM Art a JOIN a.Loan_id l WHERE l.User_id = :user")
})


public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    private double price;

    @Column(columnDefinition = "BLOB")
    @Lob
    private byte[] img;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean available;


    private String description;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "User_id")
    private User User_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Loan_id")
    private LoanedArts Loan_id;



    public Art() {
    }

    public Art(String name, double price, byte[] img, boolean available, String description, User user) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.available = available;
        this.description = description;
        this.User_id = user;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LoanedArts getLoan_id() {
        return Loan_id;
    }

    public void setLoan_id(LoanedArts loan_id) {
        Loan_id = loan_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Art art = (Art) o;
        return id == art.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public User getUser_id() {
        return User_id;
    }

    public void setUser_id(User user_id) {
        this.User_id = user_id;
    }
}
