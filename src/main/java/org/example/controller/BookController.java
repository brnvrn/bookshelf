package org.example.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.service.BookService;
import org.example.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public String listBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("title"));
        Page<Book> bookPage;

        if (title != null) {
            bookPage = bookService.findByTitle(title, pageable);
        } else if (brand != null) {
            bookPage = bookService.findByBrand(brand, pageable);
        } else if (year != null) {
            bookPage = bookService.findByYear(year, pageable);
        } else {
            bookPage = bookService.findAll(pageable);
        }

        model.addAttribute("books", bookPage);
        model.addAttribute("filterTitle", title);
        model.addAttribute("filterBrand", brand);
        model.addAttribute("filterYear", year);

        return "books/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        model.addAttribute("book", book);
        return "books/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
