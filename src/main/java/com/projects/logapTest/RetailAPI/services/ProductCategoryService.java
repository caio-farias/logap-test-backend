package com.projects.logapTest.RetailAPI.services;

import com.projects.logapTest.RetailAPI.utils.PaginatedResponse;
import com.projects.logapTest.RetailAPI.dto.ProductCategoryDTO;
import com.projects.logapTest.RetailAPI.entities.ProductCategory;
import com.projects.logapTest.RetailAPI.utils.ProductCategoryReport;
import com.projects.logapTest.RetailAPI.repositories.ProductCategoryRepository;
import com.projects.logapTest.RetailAPI.utils.exceptions.ApiRequestException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductCategoryService {
  private final ProductCategoryRepository productCategoryRepository;

  @Autowired
  public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
    this.productCategoryRepository = productCategoryRepository;
  }


  @PostConstruct
  public void createInitialProductCategories() {
    List<ProductCategory> initialProductCategoriesList = Stream.of(
      "Frutas",
      "Verduras",
      "Cereais",
      "Legumes",
      "Eletrônicos",
      "Frios",
      "Brinquedos",
      "Esporte",
      "Informática",
      "Livros",
      "Música",
      "Educação",
      "Lazer",
      "Higiene",
      "Casa",
      "Móveis"
    ).map((productCategory) -> ProductCategory
                                 .builder()
                                 .name(productCategory)
                                 .build()).collect(Collectors.toList());
    productCategoryRepository.saveAll(initialProductCategoriesList);
  }

  public ProductCategory createProductCategory(@NotNull ProductCategory productCategory) {
    boolean
      productCategoryAlreadyExists =
      productCategoryRepository
        .findByName(productCategory.getName())
        .isPresent();

    if (productCategoryAlreadyExists) {
      throw new ApiRequestException("Product category already exists.");
    }
    return productCategoryRepository.save(productCategory);
  }

  public PaginatedResponse<Page<ProductCategory>> getAll(
    Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    ).withSort(Sort.by("name"));

    Page<ProductCategory>
      productCategoryPage =
      productCategoryRepository.findAll(pageable);

    return new PaginatedResponse<>(productCategoryPage);
  }

  public PaginatedResponse<Page<ProductCategoryReport>> getAllOutOfStockProductCategories(
    Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    );

    Page<ProductCategoryReport>
      productCategoryPage =
      productCategoryRepository.findAllOutOfStockProductCategories(pageable);

    return new PaginatedResponse<>(productCategoryPage);
  }

  public PaginatedResponse<Page<ProductCategory>> getAllByNameContains(
    String name, Integer offset, Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(
      offset,
      pageSize
    ).withSort(Sort.by("name"));

    Page<ProductCategory>
      productCategoryPage =
      productCategoryRepository.findAllByNameContains(
        name,
        pageable
      );

    return new PaginatedResponse<>(productCategoryPage);
  }

  public ProductCategory getProductCategoryById(Long productCategoryId) {
    return productCategoryRepository
             .findById(productCategoryId)
             .orElseThrow(() -> new ApiRequestException("Product category doesn't exist."));
  }

  @Transactional
  public ProductCategory updateProductCategoryName(
    Long productCategoryId, @NotNull ProductCategoryDTO productCategoryDTO
  ) {
    ProductCategory
      productCategory =
      productCategoryRepository
        .findById(productCategoryId)
        .orElseThrow(() -> new ApiRequestException("Product category doesn't exist."));

    boolean
      nameAlreadyTaken =
      productCategoryRepository
        .findByName(productCategory.getName())
        .isPresent();

    if (nameAlreadyTaken) {
      throw new ApiRequestException("Product category name already exists.");
    }

    String name = productCategoryDTO.getName();
    if (name != null && name.length() > 0 && !Objects.equals(
      name,
      productCategory.getName()
    )) {
      productCategory.setName(name);
    }

    return productCategory;
  }

  @Transactional
  public void deleteProductCategory(Long productCategoryId) {
    boolean
      nonExistentProductCategory =
      productCategoryRepository.findById(productCategoryId).isEmpty();
    if (nonExistentProductCategory) {
      throw new ApiRequestException("Product category doesn't exist.");
    }

    productCategoryRepository.deleteById(productCategoryId);
  }

}
