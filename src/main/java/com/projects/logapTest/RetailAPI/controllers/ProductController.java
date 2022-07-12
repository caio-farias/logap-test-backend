package com.projects.logapTest.RetailAPI.controllers;

import com.projects.logapTest.RetailAPI.utils.PaginatedResponse;
import com.projects.logapTest.RetailAPI.dto.ProductDTO;
import com.projects.logapTest.RetailAPI.entities.Product;
import com.projects.logapTest.RetailAPI.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {
  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping("{productCategoryId}/{productSupplierId}")
  public Product createProduct(
    @PathVariable Long productCategoryId,
    @PathVariable Long productSupplierId,
    @RequestBody Product product
  ) {
    return productService.createProduct(
      productCategoryId,
      productSupplierId,
      product
    );
  }

  @PatchMapping("{productId}")
  public Product updateProduct(
    @PathVariable Long productId, @RequestBody ProductDTO productDTO
  ) {
    return productService.updateProduct(
      productId,
      productDTO
    );
  }

  @GetMapping("{productId}")
  public Product getProductById(@PathVariable Long productId) {
    return productService.getProductById(productId);
  }

  @GetMapping()
  public PaginatedResponse<Page<Product>> getAllProducts(

    @RequestParam Integer offset, @RequestParam Integer pageSize
  ) {
    return productService.getAll(
      offset,
      pageSize
    );
  }

  @GetMapping("/owned/{productSupplierId}")
  public PaginatedResponse<Page<Product>> getAllProductsOwnedBySupplier(
    @PathVariable Long productSupplierId,
    @RequestParam Integer offset,
    @RequestParam Integer pageSize
  ) {
    return productService.getAllProductsOwnedByProductSupplierById(
      productSupplierId,
      offset,
      pageSize
    );
  }

  @GetMapping("/not-owned/{productSupplierId}")
  public PaginatedResponse<Page<Product>> getAllProductsNotOwnedBySupplier(
    @PathVariable Long productSupplierId,
    @RequestParam Integer offset,
    @RequestParam Integer pageSize
  ) {
    return productService.getAllProductsNotOwnedByProductSupplierById(
      productSupplierId,
      offset,
      pageSize
    );
  }

  @DeleteMapping("{productId}")
  public void deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
  }

}
