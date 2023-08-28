package com.fistkim.studyreviewapp.sra.category.infra;

import com.fistkim.studyreviewapp.sra.category.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select distinct c from Category c left outer join fetch c.units where c.id = :id")
    Optional<Category> findByIdWithUnits(Long id);
}
