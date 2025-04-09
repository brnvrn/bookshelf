package org.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitle(String title, Pageable pageable);

    Page<Book> findByBrand(String brand, Pageable pageable);

    Page<Book> findByYear(Integer year, Pageable pageable);
}
