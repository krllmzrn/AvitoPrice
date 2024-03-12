package com.example.avito.models;

import jakarta.persistence.*;

@Entity
public class DiscountMatrix {
    @EmbeddedId
    private DiscountMatrixId id;

    @Basic
    @Column(name = "price")
    private int price;

    public DiscountMatrixId getId() {
        return id;
    }

    public void setId(DiscountMatrixId id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
