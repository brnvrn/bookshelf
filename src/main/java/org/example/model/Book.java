package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "book")
@Accessors(chain = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "vendor_code", unique = true, nullable = false)
    Integer VendorCode;
    @Column(nullable = false)
    String title;
    @Column(name = "publication_year", nullable = false)
    int year;
    @Column(nullable = false)
    String brand;
    @Column(nullable = false)
    Integer stock;
    @Column(nullable = false)
    Double price;
}
