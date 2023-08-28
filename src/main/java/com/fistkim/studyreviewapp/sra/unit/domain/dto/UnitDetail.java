package com.fistkim.studyreviewapp.sra.unit.domain.dto;

import com.fistkim.studyreviewapp.sra.unit.domain.model.Unit;
import com.fistkim.studyreviewapp.type.CategoryType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UnitDetail(
        Long id, LocalDate reviewDate, String question, String answer, int currentReviewPeriodDays,
        LocalDateTime createdAt, String categoryName, Long categoryId, CategoryType categoryType
) {

    public static UnitDetail fromUnit(Unit unit) {
        return new UnitDetail(
                unit.getId(),
                unit.getReviewDate(),
                unit.getQuestion(),
                "",//unit.getAnswer(),
                unit.getCurrentReviewPeriod().getDays(),
                unit.getCreatedAt(),
                unit.getCategory().getName(),
                unit.getCategory().getId(),
                unit.getCategory().getCategoryType()
        );
    }
}
