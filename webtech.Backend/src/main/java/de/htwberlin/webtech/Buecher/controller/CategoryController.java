package de.htwberlin.webtech.Buecher.controller;

import de.htwberlin.webtech.Buecher.model.Category;
import de.htwberlin.webtech.Buecher.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

}
