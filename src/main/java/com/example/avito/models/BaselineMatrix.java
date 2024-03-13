package com.example.avito.models;

import jakarta.persistence.*;


@Entity
@Table(name = "baseline_matrix_1")
public class BaselineMatrix {
    @EmbeddedId
    private BaselineMatrixId id;

    @Basic
    @Column(name = "price")
    private int price;
}
