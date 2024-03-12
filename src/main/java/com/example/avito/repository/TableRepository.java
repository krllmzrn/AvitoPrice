package com.example.avito.repository;

import com.example.avito.models.DiscountMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<DiscountMatrix, Integer> {

}
