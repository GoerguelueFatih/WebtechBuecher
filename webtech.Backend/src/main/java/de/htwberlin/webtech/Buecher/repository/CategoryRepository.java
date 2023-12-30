package de.htwberlin.webtech.Buecher.repository;

import de.htwberlin.webtech.Buecher.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
