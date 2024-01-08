// Update your CategoryService
package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.model.Category;
import de.htwberlin.webtech.Buecher.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        String categoryName = category.getName();
        List<Category> existingCategories = categoryRepository.findByName(categoryName);

        if (existingCategories.isEmpty()) {
            categoryRepository.save(category);
        } else {
            category = existingCategories.get(0);
        }
        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> searchByCategory(String name) {
        return categoryRepository.findByName(name);
    }

    @Transactional
    public void deleteCategory(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }
}
