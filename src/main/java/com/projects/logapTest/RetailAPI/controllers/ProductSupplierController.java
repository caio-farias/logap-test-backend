package com.projects.logapTest.RetailAPI.controllers;

import com.projects.logapTest.RetailAPI.utils.PaginatedResponse;
import com.projects.logapTest.RetailAPI.dto.ProductSupplierDTO;
import com.projects.logapTest.RetailAPI.entities.ProductSupplier;
import com.projects.logapTest.RetailAPI.dto.ProductSupplierUpdateProductsDTO;
import com.projects.logapTest.RetailAPI.services.ProductSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product-supplier")
public class ProductSupplierController {
  private final ProductSupplierService productSupplierService;

  @Autowired
  public ProductSupplierController(ProductSupplierService productSupplierService) {
    this.productSupplierService = productSupplierService;
  }

  @PostMapping()
  public ProductSupplier createProductSupplier(
    @RequestBody ProductSupplier productSupplier
  ) {
    return productSupplierService.createProductSupplier(productSupplier);
  }

  @GetMapping("{productSupplierId}")
  public ProductSupplier getProductSupplierById(
    @PathVariable Long productSupplierId
  ) {
    return productSupplierService.getProductSupplierById(productSupplierId);
  }

  @GetMapping()
  public PaginatedResponse<Page<ProductSupplier>> getAllProductSuppliers(
    @RequestParam Integer offset, @RequestParam Integer pageSize
  ) {
    return productSupplierService.getAll(
      offset,
      pageSize
    );
  }


  @PatchMapping("{productSupplierId}")
  public ProductSupplier updateProductSupplierInfo(
    @PathVariable Long productSupplierId,
    @RequestBody ProductSupplierDTO productSupplierDTO
  ) {

    return productSupplierService.updateProductSupplier(
      productSupplierId,
      productSupplierDTO
    );
  }

  @PatchMapping("/add/{productSupplierId}")
  public ProductSupplier addProductSupplierProducts(
    @PathVariable Long productSupplierId,
    @RequestBody
    ProductSupplierUpdateProductsDTO productSupplierUpdateProductsDTO
  ) {
    return productSupplierService.addProductSupplierProducts(
      productSupplierId,
      productSupplierUpdateProductsDTO
    );
  }

  @PatchMapping("/remove/{productSupplierId}")
  public ProductSupplier removeProductSupplierProducts(
    @PathVariable Long productSupplierId,
    @RequestBody
    ProductSupplierUpdateProductsDTO productSupplierUpdateProductsDTO
  ) {
    return productSupplierService.removeProductSupplierProducts(
      productSupplierId,
      productSupplierUpdateProductsDTO
    );
  }

  @DeleteMapping("{productSupplierId}")
  public void deleteProductSupplier(
    @PathVariable Long productSupplierId
  ) {
    productSupplierService.deleteProductSupplier(productSupplierId);
  }
}
