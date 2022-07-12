package com.projects.logapTest.RetailAPI.services;


import com.projects.logapTest.RetailAPI.entities.ProductSupplier;
import com.projects.logapTest.RetailAPI.repositories.ProductSupplierRepository;
import com.projects.logapTest.RetailAPI.utils.PaginatedResponse;
import com.projects.logapTest.RetailAPI.entities.ProductCategory;
import com.projects.logapTest.RetailAPI.dto.ProductDTO;
import com.projects.logapTest.RetailAPI.entities.Product;
import com.projects.logapTest.RetailAPI.repositories.ProductRepository;
import com.projects.logapTest.RetailAPI.utils.exceptions.ApiException;
import com.projects.logapTest.RetailAPI.utils.exceptions.ApiRequestException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductSupplierRepository productSupplierRepository;
  private final ProductCategoryService productCategoryService;

  @Autowired
  public ProductService(
    ProductRepository productRepository,
    ProductSupplierRepository productSupplierRepository,
    ProductCategoryService productCategoryService
  ) {
    this.productRepository = productRepository;
    this.productSupplierRepository = productSupplierRepository;
    this.productCategoryService = productCategoryService;
  }

  public Product createProduct(
    Long productCategoryId, Long productSupplierId, @NotNull Product product
  ) {
    ProductCategory
      productCategory =
      productCategoryService.getProductCategoryById(productCategoryId);

    ProductSupplier
      productSupplier =
      productSupplierRepository
        .findById(productSupplierId)
        .orElseThrow(() -> new ApiRequestException("Product supplier doesn't exist."));

    boolean
      productAlreadyExists =
      productRepository.findByName(product.getName()).isPresent();
    
    if (productAlreadyExists) {
      throw new ApiRequestException("Product already exists.");
    }

    product.setProductSupplier(productSupplier);
    product.setProductCategory(productCategory);
    return productRepository.save(product);
  }


  public Product getProductById(Long productId) {
    return productRepository
             .findById(productId)
             .orElseThrow(() -> new ApiRequestException("Product doesn't exist."));
  }

  public List<Product> getAllProductsById(List<Long> productsId) {
    return productRepository.findAllById(productsId);
  }

  public PaginatedResponse<Page<Product>> getAll(
    Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    ).withSort(Sort.by("name"));


    Page<Product> productPage = productRepository.findAll(pageable);

    return new PaginatedResponse<>(productPage);
  }


  public PaginatedResponse<Page<Product>> getAllByNameContains(
    String name, Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    ).withSort(Sort.by("name"));

    Page<Product> productPage = productRepository.findAllByNameContains(
      name,
      pageable
    );

    return new PaginatedResponse<>(productPage);
  }


  public PaginatedResponse<Page<Product>> getAllOutOfStockProducts(
    Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    ).withSort(Sort.by("name"));

    final Integer quantity = 0;
    Page<Product> productPage = productRepository.findAllByQuantity(
      quantity,
      pageable
    );

    return new PaginatedResponse<>(productPage);
  }

  public PaginatedResponse<Page<Product>> getAllProductsNotOwnedByProductSupplierById(
    Long productSupplierId, Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    );

    Page<Product>
      productPage =
      productRepository.findAllProductsNotOwnedByProductSupplierById(
        productSupplierId,
        pageable
      );

    return new PaginatedResponse<>(productPage);
  }

  public PaginatedResponse<Page<Product>> getAllProductsOwnedByProductSupplierById(
    Long productSupplierId, Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    );

    Page<Product>
      productPage =
      productRepository.findAllProductsOwnedByProductSupplierById(
        productSupplierId,
        pageable
      );

    return new PaginatedResponse<>(productPage);
  }

  @Transactional
  public Product updateProduct(Long productId, @NotNull ProductDTO productDTO) {
    Product
      product =
      productRepository
        .findById(productId)
        .orElseThrow(() -> new ApiRequestException("Product doesn't exist."));

    String name = productDTO.getName();
    if (name != null && name.length() > 0 && !Objects.equals(
      name,
      product.getName()
    )) {
      product.setName(name);
    }

    String description = productDTO.getDescription();
    if (description != null && description.length() > 0 && !Objects.equals(
      description,
      product.getDescription()
    )) {
      product.setDescription(description);
    }

    Long productCategoryId = productDTO.getProductCategoryId();

    ProductCategory
      productCategory =
      productCategoryService.getProductCategoryById(productCategoryId);

    if (productCategory != null &&
        productCategory.getName().length() > 0 &&
        !Objects.equals(
          productCategory,
          product.getProductCategory()
        )) {
      product.setProductCategory(productCategory);
    }

    int quantity = productDTO.getQuantity();
    if (quantity >= 0 && !Objects.equals(
      quantity,
      product.getQuantity()
    )) {
      product.setQuantity(quantity);
    }

    return product;
  }

  @Transactional
  public void deleteProduct(Long productId) {
    boolean
      nonexistentProduct =
      productRepository.findById(productId).isEmpty();
    if (nonexistentProduct) {
      throw new ApiRequestException("Product doesn't exist.");
    }
    productRepository.deleteById(productId);
  }

}
