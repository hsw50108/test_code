package me.dongwook.dayonetest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_score")
@Entity
public class StudentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    private Long id;

    private String exam;

    private String studentName;

    private Integer korScore;

    private Integer englishScore;

    private Integer mathScore;


}
