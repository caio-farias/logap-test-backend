package com.projects.logapTest.RetailAPI.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
  name = "product_supplier",
  uniqueConstraints = { @UniqueConstraint(
    name = "product_supplier_name_unique",
    columnNames = "name"
  ), @UniqueConstraint(
    name = "product_supplier_email_unique",
    columnNames = "email"
  ) }
)
@Data
/*@ToString(of = { "id", "name", "email", "mobileNumber", "address" })
@EqualsAndHashCode(of = { "id", "name", "email", "mobileNumber", "address" })*/

public class ProductSupplier {
  @Id
  @SequenceGenerator(
    name = "product_supplier_sequence",
    sequenceName = "product_supplier_sequence",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = SEQUENCE,
    generator = "product_supplier_sequence"
  )
  @Column(name = "product_supplier_id")
  private Long id;

  @Column(
    name = "name",
    nullable = false
  )
  private String name;

  @Column(
    name = "email",
    nullable = false
  )
  private String email;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @Column(name = "address")
  private String address;


  @OneToMany(
    mappedBy = "productSupplier",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
  )
  @JsonManagedReference
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Product> products = new HashSet<>();
}
