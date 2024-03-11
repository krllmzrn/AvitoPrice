package com.example.avito.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "baseline_matrix_1")
public class BaselineMatrix {
    @EmbeddedId
    private BaselineMatrixId id;

    @Basic
    @Column(name = "price")
    private int price;

    public BaselineMatrixId getId() {
        return id;
    }

    public void setId(BaselineMatrixId id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
