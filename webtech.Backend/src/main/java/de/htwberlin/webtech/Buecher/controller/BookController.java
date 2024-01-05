package de.htwberlin.webtech.Buecher.controller;

import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String title) {
        if (title != null) {
            return bookService.searchByTitle(title);
        }
        return bookService.getAllBooks();
    }

    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book updatedBook){
        return bookService.updateBook(isbn, updatedBook);
    }


    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn){
        bookService.deleteBook(isbn);
    }

}
