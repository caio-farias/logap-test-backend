package com.projects.logapTest.RetailAPI.services;

import com.projects.logapTest.RetailAPI.utils.PaginatedResponse;
import com.projects.logapTest.RetailAPI.entities.Product;
import com.projects.logapTest.RetailAPI.dto.ProductSupplierDTO;
import com.projects.logapTest.RetailAPI.entities.ProductSupplier;
import com.projects.logapTest.RetailAPI.dto.ProductSupplierUpdateProductsDTO;
import com.projects.logapTest.RetailAPI.repositories.ProductSupplierRepository;
import com.projects.logapTest.RetailAPI.utils.EmailValidator;
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
public class ProductSupplierService {

  private final ProductSupplierRepository productSupplierRepository;
  private final ProductService productService;

  @Autowired
  public ProductSupplierService(
    ProductSupplierRepository productSupplierRepository,
    ProductService productService
  ) {
    this.productService = productService;
    this.productSupplierRepository = productSupplierRepository;
  }

  public ProductSupplier getProductSupplierById(Long productSupplierId) {
    return productSupplierRepository
             .findById(productSupplierId)
             .orElseThrow(() -> new ApiRequestException("Product supplier doesn't exist."));
  }

  public ProductSupplier createProductSupplier(@NotNull ProductSupplier productSupplier) {
    boolean
      productSupplierAlreadyExists =
      productSupplierRepository
        .findByName(productSupplier.getName())
        .isPresent();
    if (productSupplierAlreadyExists) {
      throw new ApiRequestException("Product supplier already exists.");
    }

    return productSupplierRepository.save(productSupplier);
  }

  public PaginatedResponse<Page<ProductSupplier>> getAllByNameContains(
    String name, Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    ).withSort(Sort.by("name"));

    Page<ProductSupplier>
      productSupplierPage =
      productSupplierRepository.findAllByNameContains(
        name,
        pageable
      );

    return new PaginatedResponse<>(productSupplierPage);
  }

  public PaginatedResponse<Page<ProductSupplier>> getAll(
    Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    ).withSort(Sort.by("name"));

    Page<ProductSupplier>
      productSupplierPage =
      productSupplierRepository.findAll(pageable);

    return new PaginatedResponse<>(productSupplierPage);
  }

  public PaginatedResponse<Page<ProductSupplier>> getAllProductSuppliersWithProductOutOfStock(
    Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    );

    Page<ProductSupplier>
      productSupplierPage =
      productSupplierRepository.findAllProductSuppliersWithProductOutOfStock(pageable);

    return new PaginatedResponse<>(productSupplierPage);
  }


  @Transactional
  public ProductSupplier updateProductSupplier(
    Long productSupplierId, @NotNull ProductSupplierDTO productSupplierDTO
  ) {
    final ProductSupplier
      productSupplier =
      productSupplierRepository
        .findById(productSupplierId)
        .orElseThrow(() -> new ApiRequestException("Product supplier doesn't exist."));

    String name = productSupplierDTO.getName();
    if (name != null && name.length() > 0 && !Objects.equals(
      name,
      productSupplier.getName()
    )) {
      productSupplier.setName(name);
    }

    String email = productSupplierDTO.getEmail();
    EmailValidator emailValidator = new EmailValidator();
    if (email != null &&
        email.length() > 0 &&
        emailValidator.validate(email) &&
        !Objects.equals(
          email,
          productSupplier.getEmail()
        )) {
      productSupplier.setEmail(email);
    }

    String mobileNumber = productSupplierDTO.getMobileNumber();
    if (mobileNumber != null && mobileNumber.length() > 0 && !Objects.equals(
      mobileNumber,
      productSupplier.getMobileNumber()
    )) {
      productSupplier.setMobileNumber(mobileNumber);
    }

    return productSupplier;
  }

  @Transactional
  public ProductSupplier addProductSupplierProducts(
    Long productSupplierId,
    @NotNull ProductSupplierUpdateProductsDTO productSupplierUpdateProductsDTO
  ) {
    List<Long> productIds = productSupplierUpdateProductsDTO.getProductIds();
    if (productIds == null || productIds.size() == 0) {
      throw new ApiRequestException("Invalid products identifiers.");
    }

    final ProductSupplier
      productSupplier =
      productSupplierRepository
        .findById(productSupplierId)
        .orElseThrow(() -> new ApiRequestException("Product supplier doesn't exist."));

    List<Product>
      toBeAddedProducts =
      productService.getAllProductsById(productIds);

    boolean
      alreadyAddedProducts =
      productSupplier.getProducts().containsAll(toBeAddedProducts);

    if (alreadyAddedProducts) {
      throw new ApiRequestException("These products were already added.");
    }

    toBeAddedProducts.forEach(product -> product.setProductSupplier(productSupplier));
    productSupplier.getProducts().addAll(toBeAddedProducts);
    return productSupplier;

  }

  @Transactional
  public ProductSupplier removeProductSupplierProducts(
    Long productSupplierId,
    @NotNull ProductSupplierUpdateProductsDTO productSupplierUpdateProductsDTO
  ) {
    List<Long> productsId = productSupplierUpdateProductsDTO.getProductIds();
    if (productsId == null || productsId.size() == 0) {
      throw new ApiRequestException("Invalid products id.");
    }
    final ProductSupplier
      productSupplier =
      productSupplierRepository
        .findById(productSupplierId)
        .orElseThrow(() -> new ApiRequestException("Product supplier doesn't exist."));

    List<Product>
      toBeRemovedProducts =
      productService.getAllProductsById(productsId);

    boolean
      containsAllToBeRemovedProducts =
      productSupplier.getProducts().containsAll(toBeRemovedProducts);

    if (!containsAllToBeRemovedProducts) {
      throw new ApiRequestException("These products are not valid.");
    }

    toBeRemovedProducts.forEach((product) -> product.setProductSupplier(null));
    productSupplier.getProducts().removeAll(toBeRemovedProducts);

    return productSupplier;
  }

  @Transactional
  public void deleteProductSupplier(Long productSupplierId) {
    boolean
      nonExistentProductSupplier =
      productSupplierRepository.findById(productSupplierId).isEmpty();
    if (nonExistentProductSupplier) {
      throw new ApiRequestException("Product supplier doesn't exist.");
    }
  }

}
