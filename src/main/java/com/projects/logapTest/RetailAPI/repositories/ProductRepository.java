package com.projects.logapTest.RetailAPI.repositories;

import com.projects.logapTest.RetailAPI.entities.Product;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findByName(String name);

  Page<Product> findAllByNameContains(String name, Pageable pageable);

  /*SELECT * FROM product WHERE product.quantity = 0*/
  Page<Product> findAllByQuantity(Integer quantity, Pageable pageable);

  @Query(
    value = "SELECT * FROM product WHERE product_supplier_id IS NULL ",
    nativeQuery = true
  )
  Page<Product> findAllProductsNotOwnedByProductSupplierById(
    @Param("id") Long productSupplierId, Pageable pageable
  );


  @Query(
    value = "SELECT * FROM product WHERE product_supplier_id = :id",
    nativeQuery = true
  )
  Page<Product> findAllProductsOwnedByProductSupplierById(
    @Param("id") Long productSupplierId, Pageable pageable
  );

  @Override
  default List<Product> findAllById(@NotNull Iterable<Long> ids) {
    List<Product> results = new ArrayList<>();
    for (Long id : ids) {
      findById(id).ifPresent(results::add);
    }
    return results;
  }
}
