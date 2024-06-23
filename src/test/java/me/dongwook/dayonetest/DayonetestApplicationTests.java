package me.dongwook.dayonetest;

import jakarta.persistence.EntityManager;
import me.dongwook.dayonetest.model.StudentScoreFixture;
import me.dongwook.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DayonetestApplicationTests extends IntegrationTest {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void contextLoads() {

        var studentScore = StudentScoreFixture.passed();
        var savedStudentScore = studentScoreRepository.save(studentScore);

        entityManager.flush();
        entityManager.clear();

        var queryStudentScore = studentScoreRepository.findById(savedStudentScore.getId()).orElseThrow();
        System.out.println("queryStudentScore.getId() = " + queryStudentScore.getId());

        Assertions.assertEquals(queryStudentScore.getId(), savedStudentScore.getId());
        Assertions.assertEquals(queryStudentScore.getStudentName(), savedStudentScore.getStudentName());
        Assertions.assertEquals(queryStudentScore.getMathScore(), savedStudentScore.getMathScore());
        Assertions.assertEquals(queryStudentScore.getKorScore(), savedStudentScore.getKorScore());
        Assertions.assertEquals(queryStudentScore.getEnglishScore(), savedStudentScore.getEnglishScore());


    }

}
