package de.htwberlin.webtech.Buecher;

import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.model.Category;
import de.htwberlin.webtech.Buecher.repository.BookRepository;
import de.htwberlin.webtech.Buecher.service.BookService;
import de.htwberlin.webtech.Buecher.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class BookTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void CreateBookSaveTest() {
        Category category = new Category(1L, "Fiction", new ArrayList<>());
        Book newBook = Book.builder()
                .isbn("123-4567890123")
                .title("Book")
                .author("Author")
                .price(new BigDecimal("19.99"))
                .releaseDate("05.03.2000")
                .category(category)
                .build();

        Mockito.when(bookRepository.save(any(Book.class))).thenReturn(newBook);
        Mockito.when(categoryService.createCategory(any(Category.class))).thenReturn(category);

        Book createdBook = bookService.createBook(newBook);

        assertNotNull(createdBook);
        assertEquals(newBook.getIsbn(), createdBook.getIsbn());
        assertEquals(newBook.getTitle(), createdBook.getTitle());
        assertEquals(newBook.getAuthor(), createdBook.getAuthor());
        assertEquals(newBook.getPrice(), createdBook.getPrice());
        assertEquals(newBook.getReleaseDate(), createdBook.getReleaseDate());
        assertEquals(newBook.getCategory(), createdBook.getCategory());
    }

}

