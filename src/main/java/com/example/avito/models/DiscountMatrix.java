package com.example.avito.models;

import jakarta.persistence.*;

@Entity
@Table(name = "discount_matrix_1")
public class DiscountMatrix {
    @EmbeddedId
    private DiscountMatrixId id;

    @Basic
    @Column(name = "price")
    private int price;
}
