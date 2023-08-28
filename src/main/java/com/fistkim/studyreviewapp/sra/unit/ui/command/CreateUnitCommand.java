package com.fistkim.studyreviewapp.sra.unit.ui.command;

public record CreateUnitCommand(
        Long categoryId,
        String question,
        String answer
) {
}
