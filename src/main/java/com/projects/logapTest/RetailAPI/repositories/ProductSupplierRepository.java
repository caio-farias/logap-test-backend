package com.projects.logapTest.RetailAPI.repositories;

import com.projects.logapTest.RetailAPI.entities.ProductSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSupplierRepository
  extends JpaRepository<ProductSupplier, Long> {
  Optional<ProductSupplier> findByName(String name);

  Page<ProductSupplier> findAllByNameContains(String name, Pageable pageable);

  /*Listagem dos fornecedores que possuem produtos faltando em estoque;*/
  @Query(
    value = "SELECT     \n" +
            "    product_supplier.product_supplier_id,\n" +
            "    product_supplier.name, \n" +
            "    product_supplier.email, \n" +
            "    product_supplier.address, \n" +
            "    product_supplier.mobile_number  \n" +
            "    FROM product_supplier \n" +
            "LEFT OUTER JOIN product ON product_supplier.product_supplier_id = product.product_supplier_id " +
            "    WHERE product.quantity = 0 \n" +
            "    AND product_supplier.product_supplier_id IS NOT NULL \n" +
            "GROUP BY product_supplier.product_supplier_id",
    nativeQuery = true
  )
  Page<ProductSupplier> findAllProductSuppliersWithProductOutOfStock(
    Pageable pageable
  );
}

