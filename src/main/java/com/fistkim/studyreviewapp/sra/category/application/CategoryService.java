package com.fistkim.studyreviewapp.sra.category.application;

import com.fistkim.studyreviewapp.sra.category.domain.dto.CategoryDetail;
import com.fistkim.studyreviewapp.sra.category.domain.model.Category;
import com.fistkim.studyreviewapp.sra.category.infra.CategoryRepository;
import com.fistkim.studyreviewapp.sra.category.ui.command.CreateCategoryCommand;
import com.fistkim.studyreviewapp.sra.category.ui.command.UpdateCategoryCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(CreateCategoryCommand command) {
        final Category category = Category.builder()
                .categoryType(command.categoryType())
                .name(command.name())
                .build();
        this.categoryRepository.save(category);
    }

    public void updateCategory(Long id, UpdateCategoryCommand command) {
        final Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        category.update(command.categoryType(), command.name());
        this.categoryRepository.save(category);
    }

    public CategoryDetail getCategory(Long id) {
        final Category category = this.categoryRepository.findByIdWithUnits(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        return CategoryDetail.fromCategory(category);
    }

    public void deleteCategory(Long id) {
        final Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
        this.categoryRepository.delete(category);
    }

}
