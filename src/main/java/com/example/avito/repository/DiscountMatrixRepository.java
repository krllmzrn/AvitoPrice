package com.example.avito.repository;



import com.example.avito.models.DiscountMatrix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiscountMatrixRepository  extends JpaRepository<DiscountMatrix,Integer> {
    Page<DiscountMatrix> findAll(Pageable pageable);

}
