package com.projects.logapTest.RetailAPI.controllers;

import com.projects.logapTest.RetailAPI.utils.PaginatedResponse;
import com.projects.logapTest.RetailAPI.entities.Product;
import com.projects.logapTest.RetailAPI.utils.ProductCategoryReport;
import com.projects.logapTest.RetailAPI.entities.ProductSupplier;
import com.projects.logapTest.RetailAPI.services.ProductCategoryService;
import com.projects.logapTest.RetailAPI.services.ProductService;
import com.projects.logapTest.RetailAPI.services.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {
  private final ProductService productService;
  private final ProductCategoryService productCategoryService;
  private final ProductSupplierService productSupplierService;

  @Autowired
  public ReportController(
    ProductService productService,
    ProductCategoryService productCategoryService,
    ProductSupplierService productSupplierService
  ) {
    this.productService = productService;
    this.productCategoryService = productCategoryService;
    this.productSupplierService = productSupplierService;
  }

  @GetMapping("product-category")
  public PaginatedResponse<Page<ProductCategoryReport>> getAllOutOfStockProductCategories(
    @RequestParam Integer offset, @RequestParam Integer pageSize
  ) {
    return productCategoryService.getAllOutOfStockProductCategories(
      offset,
      pageSize
    );
  }

  @GetMapping("product")
  public PaginatedResponse<Page<Product>> getAllOutOfStockProducts(
    @RequestParam Integer offset, @RequestParam Integer pageSize
  ) {
    return productService.getAllOutOfStockProducts(
      offset,
      pageSize
    );
  }

  @GetMapping("product-supplier")
  public PaginatedResponse<Page<ProductSupplier>> getAllProductSuppliersWithProductOutOfStock(
    @RequestParam Integer offset, @RequestParam Integer pageSize
  ) {
    return productSupplierService.getAllProductSuppliersWithProductOutOfStock(
      offset,
      pageSize
    );
  }
}
