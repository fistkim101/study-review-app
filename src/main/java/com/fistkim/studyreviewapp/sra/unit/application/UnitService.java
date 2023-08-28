package com.fistkim.studyreviewapp.sra.unit.application;

import com.fistkim.studyreviewapp.sra.category.domain.model.Category;
import com.fistkim.studyreviewapp.sra.category.infra.CategoryRepository;
import com.fistkim.studyreviewapp.sra.unit.domain.dto.UnitDetail;
import com.fistkim.studyreviewapp.sra.unit.domain.model.Unit;
import com.fistkim.studyreviewapp.sra.unit.infra.UnitRepository;
import com.fistkim.studyreviewapp.sra.unit.ui.command.CreateUnitCommand;
import com.fistkim.studyreviewapp.sra.unit.ui.command.UpdateUnitCommand;
import com.fistkim.studyreviewapp.type.ReviewPeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final CategoryRepository categoryRepository;
    private final UnitRepository unitRepository;

    public UnitDetail createUnit(CreateUnitCommand command) {
        final Category category = this.categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        final LocalDateTime now = LocalDateTime.now();
        final Unit unit = Unit.builder()
                .reviewDate(now.toLocalDate().plusDays(1L))
                .question(command.question())
                .answer(command.answer())
                .currentReviewPeriod(ReviewPeriod.FIRST)
                .category(category)
                .build();
        category.addUnit(unit);
        this.categoryRepository.save(category);

        return UnitDetail.fromUnit(unit);
    }

    public UnitDetail updateUnit(Long unitId, UpdateUnitCommand command) {
        final Unit unit = this.unitRepository.findById(unitId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Unit 입니다."));

        unit.update(command.question(), command.answer());
        final Unit updatedUnit = this.unitRepository.save(unit);

        return UnitDetail.fromUnit(updatedUnit);
    }

    public UnitDetail reviewUnit(Long unitId, boolean isCorrect) {
        final Unit unit = this.unitRepository.findById(unitId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Unit 입니다."));

        unit.review(isCorrect);
        final Unit reviewedUnit = this.unitRepository.save(unit);
        return UnitDetail.fromUnit(reviewedUnit);
    }

    public List<UnitDetail> getUnits(LocalDate reviewDate) {
        List<Unit> units = this.unitRepository.findAllByReviewDateBeforeOrEqual(reviewDate);
        return units.stream()
                .map(UnitDetail::fromUnit)
                .collect(Collectors.toList());
    }

    public void deleteUnit(Long unitId) {
        final Unit unit = this.unitRepository.findById(unitId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Unit 입니다."));
        this.unitRepository.delete(unit);
    }

}
