package com.example.avito.repository;

import com.example.avito.models.BaselineMatrix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaselineMatrixRepository extends JpaRepository<BaselineMatrix,Integer> {
    Page<BaselineMatrix> findAll(Pageable pageable);
}
