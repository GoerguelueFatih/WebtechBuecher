package de.htwberlin.webtech.Buecher.repository;

import de.htwberlin.webtech.Buecher.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByCategoryName(String categoryName);
    Book findByIsbn(String isbn);
}
