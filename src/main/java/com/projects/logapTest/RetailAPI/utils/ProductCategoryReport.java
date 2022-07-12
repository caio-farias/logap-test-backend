package com.projects.logapTest.RetailAPI.utils;

import org.springframework.beans.factory.annotation.Value;

public interface ProductCategoryReport {
  @Value("#{target.product_category_id}")
  Long getId();

  String getName();

  @Value("#{target.total_products}")
  Integer getTotalProducts();

  @Value("#{target.total_in_stock}")
  Integer getTotalInStock();
}
