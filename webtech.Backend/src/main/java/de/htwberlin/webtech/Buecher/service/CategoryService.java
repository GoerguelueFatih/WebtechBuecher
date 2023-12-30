package de.htwberlin.webtech.Buecher.service;

import de.htwberlin.webtech.Buecher.repository.CategoryRepository;
import de.htwberlin.webtech.Buecher.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category){
        category.setId(UUID.randomUUID().toString());
        categoryRepository.save(category);
        return category;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

}
