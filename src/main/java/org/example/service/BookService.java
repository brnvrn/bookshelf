package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setVendorCode(bookDetails.getVendorCode());
        book.setTitle(bookDetails.getTitle());
        book.setYear(bookDetails.getYear());
        book.setBrand(bookDetails.getBrand());
        book.setStock(bookDetails.getStock());
        book.setPrice(bookDetails.getPrice());
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<Book> findByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitle(title, pageable);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Page<Book> findByBrand(String brand, Pageable pageable) {
        return bookRepository.findByBrand(brand, pageable);
    }

    public Page<Book> findByYear(Integer year, Pageable pageable) {
        return bookRepository.findByYear(year, pageable);
    }

}
