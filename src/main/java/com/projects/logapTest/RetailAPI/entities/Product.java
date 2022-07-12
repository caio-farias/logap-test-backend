package com.projects.logapTest.RetailAPI.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(
  name = "product"
)
public class Product {
  @Id
  @SequenceGenerator(
    name = "product_sequence",
    sequenceName = "product_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = SEQUENCE,
    generator = "product_sequence"
  )
  @Column(
    name = "product_id",
    nullable = false
  )
  private Long id;

  @ManyToOne(
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER
  )
  @JoinColumn(
    name = "product_category_id",
    referencedColumnName = "product_category_id"
  )
  private ProductCategory productCategory;

  @ManyToOne(
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  @JoinColumn(
    name = "product_supplier_id",
    referencedColumnName = "product_supplier_id"
  )
  @JsonBackReference
  private ProductSupplier productSupplier;

  @Column(
    name = "name",
    nullable = false
  )
  private String name;

  @Column(
    name = "price",
    nullable = false
  )
  private double price;

  @Column(name = "description")
  private String description;

  @Column(
    name = "quantity",
    nullable = false
  )
  private int quantity;
}
