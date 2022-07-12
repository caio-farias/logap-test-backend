package com.projects.logapTest.RetailAPI.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductSupplierDTO {
  private String name;
  private String email;
  private String mobileNumber;
  private String address;
}
