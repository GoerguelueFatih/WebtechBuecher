package de.htwberlin.webtech.Buecher.controller;

import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam(required = false) String title,
                                                  @RequestParam(required = false) String author) {
        List<Book> books;
        if (title != null && !title.trim().isEmpty()) {
            books = bookService.searchByTitle(title);
        } else if (author != null && !author.trim().isEmpty()) {
            books = bookService.searchByAuthor(author);
        } else {
            books = bookService.getAllBooks();
        }
        return ResponseEntity.ok(books);
    }


    @GetMapping
    public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam(required = false) String category) {
        if (category != null && !category.trim().isEmpty()) {
            List<Book> books = bookService.getBooksByCategory(category);
            return ResponseEntity.ok(books);
        }
        return ResponseEntity.ok(bookService.getAllBooks());
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
