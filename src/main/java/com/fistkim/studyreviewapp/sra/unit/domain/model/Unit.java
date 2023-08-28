package com.fistkim.studyreviewapp.sra.unit.domain.model;

import com.fistkim.studyreviewapp.sra.category.domain.model.Category;
import com.fistkim.studyreviewapp.type.ReviewPeriod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "unit",
        indexes = {
                @Index(name = "idx_review_date", columnList = "review_date")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(value = AuditingEntityListener.class)
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_date", nullable = false)
    @Comment(value = "리뷰일")
    private LocalDate reviewDate;

    @Column(name = "question", nullable = false)
    @Comment(value = "질문")
    private String question;

    @Column(name = "answer", nullable = false)
    @Comment(value = "답변")
    private String answer;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_review_period", nullable = false)
    @Comment(value = "리뷰주기")
    private ReviewPeriod currentReviewPeriod;

    @Column(name = "created_at", nullable = false)
    @Comment(value = "생성일시")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Comment(value = "수정일시")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    public void update(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void review(boolean isCorrect) {
        if (isCorrect) {
            this.reviewDate = this.reviewDate.plusDays(this.currentReviewPeriod.getDays());
            this.currentReviewPeriod = this.currentReviewPeriod.next();
            return;
        }

        this.reviewDate = this.reviewDate.plusDays(1L);
    }
}
