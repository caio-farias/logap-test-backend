package com.projects.logapTest.RetailAPI.controllers;

import com.projects.logapTest.RetailAPI.utils.PaginatedResponse;
import com.projects.logapTest.RetailAPI.dto.ProductCategoryDTO;
import com.projects.logapTest.RetailAPI.entities.ProductCategory;
import com.projects.logapTest.RetailAPI.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product-category")
public class ProductCategoryController {
  private final ProductCategoryService productCategoryService;

  @Autowired
  public ProductCategoryController(ProductCategoryService productCategoryService) {
    this.productCategoryService = productCategoryService;
  }

  @PostMapping()
  public ProductCategory createProductCategory(
    @RequestBody ProductCategory productCategory
  ) {
    return productCategoryService.createProductCategory(productCategory);
  }

  @GetMapping("{productCategoryId}")
  public ProductCategory getProductCategoryById(
    @PathVariable Long productCategoryId
  ) {
    return productCategoryService.getProductCategoryById(productCategoryId);
  }

  @GetMapping()
  public PaginatedResponse<Page<ProductCategory>> getAllProductCategories(
    @RequestParam Integer offset, @RequestParam Integer pageSize
  ) {
    return productCategoryService.getAll(
      offset,
      pageSize
    );
  }

  @PatchMapping("{productCategoryId}")
  public ProductCategory updateProductCategory(
    @PathVariable Long productCategoryId,
    @RequestBody ProductCategoryDTO productCategoryDTO
  ) {
    return productCategoryService.updateProductCategoryName(
      productCategoryId,
      productCategoryDTO
    );
  }

  @DeleteMapping("{productCategoryId}")
  public void deleteProductCategory(
    @PathVariable Long productCategoryId
  ) {
    productCategoryService.deleteProductCategory(productCategoryId);
  }
}
