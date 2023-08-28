package com.fistkim.studyreviewapp.sra.category.domain.dto;

import com.fistkim.studyreviewapp.sra.category.domain.model.Category;
import com.fistkim.studyreviewapp.sra.unit.domain.dto.UnitDetail;
import com.fistkim.studyreviewapp.type.CategoryType;

import java.time.LocalDateTime;
import java.util.List;

public record CategoryDetail(Long id, CategoryType categoryType, String name, LocalDateTime createdAt,
                             List<UnitDetail> units) {

    public static CategoryDetail fromCategory(Category category) {
        List<UnitDetail> units = category.getUnits().stream()
                .map(UnitDetail::fromUnit)
                .toList();

        return new CategoryDetail(category.getId(), category.getCategoryType(), category.getName(), category.getCreatedAt(), units);
    }
}
