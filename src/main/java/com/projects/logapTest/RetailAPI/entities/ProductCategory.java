package com.projects.logapTest.RetailAPI.entities;


import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(
  name = "product_category",
  uniqueConstraints = { @UniqueConstraint(
    name = "product_category_name_unique",
    columnNames = "name"
  ) }
)
public class ProductCategory {
  @Id
  @SequenceGenerator(
    name = "product_category_sequence",
    sequenceName = "product_category_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = SEQUENCE,
    generator = "product_category_sequence"
  )
  @Column(
    name = "product_category_id",
    nullable = false
  )
  private Long id;

  @Column(
    name = "name",
    nullable = false
  )
  private String name;
}
