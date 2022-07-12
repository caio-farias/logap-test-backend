package com.projects.logapTest.RetailAPI.dto;

import com.projects.logapTest.RetailAPI.entities.ProductCategory;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDTO {
  private String name;
  private Long productCategoryId;
  private ProductCategory productCategory;
  private String description;
  private int quantity;

}
