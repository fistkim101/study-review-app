package com.fistkim.studyreviewapp.sra.category.domain.model;

import com.fistkim.studyreviewapp.sra.unit.domain.model.Unit;
import com.fistkim.studyreviewapp.type.CategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(value = AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    @Comment(value = "카테고리 타입")
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "name", nullable = false)
    @Comment(value = "이름")
    private String name;

    @Column(name = "created_at", nullable = false)
    @Comment(value = "생성일시")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Comment(value = "수정일시")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Unit> units = new ArrayList<>();

    public void update(CategoryType categoryType, String name) {
        this.categoryType = categoryType;
        this.name = name;
    }

    public void addUnit(Unit unit) {
        this.units.add(unit);
    }

}
