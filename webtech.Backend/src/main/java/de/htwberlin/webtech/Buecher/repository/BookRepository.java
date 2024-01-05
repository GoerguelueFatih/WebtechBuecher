package de.htwberlin.webtech.Buecher.repository;

import de.htwberlin.webtech.Buecher.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByTitle(String title);
    Book findByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
