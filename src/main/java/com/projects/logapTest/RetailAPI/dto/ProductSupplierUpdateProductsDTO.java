package com.projects.logapTest.RetailAPI.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductSupplierUpdateProductsDTO {
  private List<Long> productIds;
}
