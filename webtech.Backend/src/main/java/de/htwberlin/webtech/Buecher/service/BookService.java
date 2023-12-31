package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.ExtraCode.ISBNGen;
import de.htwberlin.webtech.Buecher.model.Category;
import de.htwberlin.webtech.Buecher.repository.BookRepository;
import de.htwberlin.webtech.Buecher.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;


    public Book createBook(Book book) {
        book.setIsbn(ISBNGen.generateISBN());
        Category existingCategory = categoryService.createCategory(book.getCategory());
        book.setCategory(existingCategory);

        bookRepository.save(book);

        // Retrieve the saved book from the database to get the generated ID
        Book savedBook = bookRepository.findByIsbn(book.getIsbn());
        savedBook.setCategoryId(existingCategory.getId());
        savedBook.setCategoryName(existingCategory.getName());

        return savedBook;
    }


    public Book updateBook(String isbn, Book updateBook){
        Book existingBook = getBookByIsbn(isbn);
        existingBook.setTitle(updateBook.getTitle());
        existingBook.setCost(updateBook.getCost());
        existingBook.setReleaseDate(updateBook.getReleaseDate());
        return saveBook(existingBook);
    }

    @Transactional
    public void deleteBook(String isbn){
        bookRepository.deleteByIsbn(isbn);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Object[]> findAllBooksAndCategories() {
        return bookRepository.findAllBooksAndCategories();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }


}

