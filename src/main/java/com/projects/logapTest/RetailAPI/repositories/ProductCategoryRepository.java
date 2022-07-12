package com.projects.logapTest.RetailAPI.repositories;

import com.projects.logapTest.RetailAPI.entities.ProductCategory;
import com.projects.logapTest.RetailAPI.utils.ProductCategoryReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository
  extends JpaRepository<ProductCategory, Long> {
  Optional<ProductCategory> findByName(String name);

  Page<ProductCategory> findAllByNameContains(String name, Pageable pageable);


  /*Listagem das categorias juntamente com suas quantidades totais de produtos em estoque;*/
  @Query(
    value = " SELECT product_category_id, name, info\n" +
            " .total_products, info\n" +
            "      .total_in_stock FROM\n" +
            "      product_category \n" +
            "      AS prod_c\n" +
            "      JOIN(\n" +
            "          SELECT product_category_id as prod_id, SUM(quantity) AS total_in_stock, COUNT\n" +
            "          (1) AS total_products FROM product \n" +
            "          GROUP BY prod_id\n" +
            "      ) AS info ON prod_c.product_category_id = prod_id\n",
    nativeQuery = true
  )
  Page<ProductCategoryReport> findAllOutOfStockProductCategories(Pageable pageable);
}
