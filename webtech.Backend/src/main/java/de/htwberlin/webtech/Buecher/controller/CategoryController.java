package de.htwberlin.webtech.Buecher.controller;

import de.htwberlin.webtech.Buecher.model.Book;
import de.htwberlin.webtech.Buecher.model.Category;
import de.htwberlin.webtech.Buecher.model.User;
import de.htwberlin.webtech.Buecher.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategory(@RequestParam(required = false) String name) {
        if (name != null) {
            return categoryService.searchByCategory(name);
        }
        return categoryService.getAllCategory();
    }

    @DeleteMapping("/{categoryid}")
    public void deleteCategory(@PathVariable String categoryid) {
        categoryService.deleteCategory(Long.valueOf(categoryid));
    }
}
