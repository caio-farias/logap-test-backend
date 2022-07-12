package com.projects.logapTest.RetailAPI.dto;

import com.sun.istack.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductCategoryDTO {
  @NotNull
  private String name;
}
