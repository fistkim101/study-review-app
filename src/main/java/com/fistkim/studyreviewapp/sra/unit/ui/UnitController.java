package com.fistkim.studyreviewapp.sra.unit.ui;

import com.fistkim.studyreviewapp.sra.unit.application.UnitService;
import com.fistkim.studyreviewapp.sra.unit.domain.dto.UnitDetail;
import com.fistkim.studyreviewapp.sra.unit.ui.command.CreateUnitCommand;
import com.fistkim.studyreviewapp.sra.unit.ui.command.UpdateUnitCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @PostMapping("/unit")
    public ResponseEntity<UnitDetail> createUnit(@RequestBody CreateUnitCommand command) {
        final UnitDetail createdUnit = this.unitService.createUnit(command);
        return ResponseEntity.ok(createdUnit);
    }

    @PutMapping("/unit/{id}")
    public ResponseEntity<UnitDetail> updateUnit(@PathVariable(value = "id") Long id, @RequestBody UpdateUnitCommand command) {
        final UnitDetail updatedUnit = this.unitService.updateUnit(id, command);
        return ResponseEntity.ok(updatedUnit);
    }

    @PutMapping("/unit/review")
    public ResponseEntity<UnitDetail> reviewUnit(@RequestParam(value = "id") Long id, @RequestParam(value = "isCorrect") boolean isCorrect) {
        final UnitDetail reviewedUnit = this.unitService.reviewUnit(id, isCorrect);
        return ResponseEntity.ok(reviewedUnit);
    }

    @GetMapping("/unit")
    public ResponseEntity<List<UnitDetail>> getUnits(@RequestParam LocalDate reviewDate) {
        final List<UnitDetail> units = this.unitService.getUnits(reviewDate);
        return ResponseEntity.ok(units);
    }

    @DeleteMapping("/unit/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable(value = "id") Long id) {
        this.unitService.deleteUnit(id);
        return ResponseEntity.ok(null);
    }

}
