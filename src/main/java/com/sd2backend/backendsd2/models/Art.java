package com.sd2backend.backendsd2.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Art")
@NamedQueries({
        @NamedQuery(name = "find_all_arts", query = "SELECT a FROM Art a")
})

public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private String name ;


    private double price ;

    @Column( columnDefinition="BLOB")
    @Lob
    private byte[] img;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean available;


    private String description;



    public Art() {
    }

    public Art(String name, double price, byte[] img, boolean available, String description) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.available = available;
        this.description = description;
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
