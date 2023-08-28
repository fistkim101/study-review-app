package com.fistkim.studyreviewapp.sra.unit.infra;

import com.fistkim.studyreviewapp.sra.unit.domain.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Query("select u from Unit u where u.reviewDate <= :reviewDate")
    List<Unit> findAllByReviewDateBeforeOrEqual(LocalDate reviewDate);

}
