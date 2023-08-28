package com.fistkim.studyreviewapp.sra.category.ui;

import com.fistkim.studyreviewapp.sra.category.application.CategoryService;
import com.fistkim.studyreviewapp.sra.category.domain.dto.CategoryDetail;
import com.fistkim.studyreviewapp.sra.category.ui.command.CreateCategoryCommand;
import com.fistkim.studyreviewapp.sra.category.ui.command.UpdateCategoryCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDetail> createCategory(@RequestBody CreateCategoryCommand command) {
        final CategoryDetail createdCategory = this.categoryService.createCategory(command);
        return ResponseEntity.ok(createdCategory);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable(value = "id") Long id, @RequestBody UpdateCategoryCommand command) {
        this.categoryService.updateCategory(id, command);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDetail> getCategory(@PathVariable(value = "id") Long id) {
        final CategoryDetail categoryDetail = this.categoryService.getCategory(id);
        return ResponseEntity.ok(categoryDetail);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "id") Long id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok(null);
    }

}

