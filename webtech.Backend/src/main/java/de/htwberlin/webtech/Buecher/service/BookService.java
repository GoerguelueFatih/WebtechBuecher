package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.ExtraCode.ISBNGen;
import de.htwberlin.webtech.Buecher.model.Category;
import de.htwberlin.webtech.Buecher.repository.BookRepository;
import de.htwberlin.webtech.Buecher.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;


    @Transactional
    public Book createBook(Book book) {
        String isbn = ISBNGen.generateISBN();
        if(bookRepository.findByIsbn(isbn) != null) {
            throw new IllegalArgumentException("A book with the generated ISBN already exists");
        }
        book.setIsbn(isbn);
        Category existingCategory = categoryService.createCategory(book.getCategory());
        book.setCategory(existingCategory);
        bookRepository.save(book);
        return book;
    }


    @Transactional
    public Book updateBook(String isbn, Book updateBook){
        Book existingBook = getBookByIsbn(isbn);
        existingBook.setTitle(updateBook.getTitle());
        existingBook.setPrice(updateBook.getPrice());
        existingBook.setReleaseDate(updateBook.getReleaseDate());
        existingBook.setAuthor(updateBook.getAuthor());
        return saveBook(existingBook);
    }


    public void deleteBook(String bookId){
        bookRepository.deleteById(bookId);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> getBooksByCategory(String categoryName) {
        return bookRepository.findByCategoryName(categoryName);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

}

