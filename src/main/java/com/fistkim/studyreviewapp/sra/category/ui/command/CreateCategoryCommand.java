package com.fistkim.studyreviewapp.sra.category.ui.command;

import com.fistkim.studyreviewapp.type.CategoryType;

public record CreateCategoryCommand(
        CategoryType categoryType,
        String name
) {
}
