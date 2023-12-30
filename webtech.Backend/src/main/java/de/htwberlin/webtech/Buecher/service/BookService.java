package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.ExtraCode.ISBNGen;
import de.htwberlin.webtech.Buecher.model.Category;
import de.htwberlin.webtech.Buecher.repository.BookRepository;
import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public Book createBook(Book book){
        book.setIsbn(ISBNGen.generateISBN());
        bookRepository.save(book);
        return book;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> searchByTitel(String titel) {
        return bookRepository.findByTitel(titel);
    }

    @Transactional
    public void deleteBook(String isbn){
        bookRepository.deleteByIsbn(isbn);
    }

    public void addCategoryToBook(String isbn, String categoryId) {
        // Step 1: Retrieve the Book
        Book book = bookRepository.findById(isbn).orElse(null);

        // Step 2: Retrieve the Category
        Category category = categoryRepository.findById(categoryId).orElse(null);

        // Step 3: Set the Category on the Book
        if (book != null && category != null) {
            book.setCategory(category);

            // Step 4: Save the Book
            bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Book or Category not found");
            // Handle the case where either the book or category is not found
            // You might want to throw an exception or handle it based on your application's logic.
        }
    }
}
