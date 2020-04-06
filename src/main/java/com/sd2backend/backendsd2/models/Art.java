package com.sd2backend.backendsd2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Art")
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String name ;


    private double price ;

    private String img;

    private boolean available;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userId")
    @JsonIgnore
    private List<User> users;

    public Art() {
    }

    public Art(String name, double price, String img, boolean available) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.available = available;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
}
