package me.dongwook.dayonetest.service;

import jakarta.persistence.EntityManager;
import me.dongwook.dayonetest.IntegrationTest;
import me.dongwook.dayonetest.MyCalculator;
import me.dongwook.dayonetest.controller.response.ExamFailStudentResponse;
import me.dongwook.dayonetest.controller.response.ExamPassStudentResponse;
import me.dongwook.dayonetest.model.StudentScore;
import me.dongwook.dayonetest.model.StudentScoreFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentScoreServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private StudentScoreService studentScoreService;

    @Autowired
    private EntityManager entityManager;

    @Test
    void savePassedStudentScoreTest() {
        //given
        StudentScore studentScore = StudentScoreFixture.passed();

        //when
        studentScoreService.saveScore(
                studentScore.getStudentName(),
                studentScore.getExam(),
                studentScore.getKorScore(),
                studentScore.getEnglishScore(),
                studentScore.getMathScore()
        );
        entityManager.flush();
        entityManager.clear();

        //then
        List<ExamPassStudentResponse> passStudentResponses = studentScoreService.getPassStudentList(studentScore.getExam());

        Assertions.assertEquals(1, passStudentResponses.size());

        ExamPassStudentResponse passStudentResponse = passStudentResponses.get(0);

        Double getAvgScore = new MyCalculator()
                .add(studentScore.getKorScore().doubleValue())
                .add(studentScore.getMathScore().doubleValue())
                .add(studentScore.getEnglishScore().doubleValue())
                .divide(3.0)
                .getResult();

        Assertions.assertEquals(studentScore.getStudentName(), passStudentResponse.getStudentName());
        Assertions.assertEquals(getAvgScore, passStudentResponse.getAvgScore());
    }

    @Test
    void saveFailedStudentScoreTest() {
        // given
        StudentScore studentScore = StudentScoreFixture.failed();

        //when
        studentScoreService.saveScore(
                studentScore.getStudentName(),
                studentScore.getExam(),
                studentScore.getKorScore(),
                studentScore.getEnglishScore(),
                studentScore.getMathScore()
        );

        entityManager.flush();
        entityManager.clear();

        //then
        List<ExamFailStudentResponse> failStudentResponses = studentScoreService.getFailStudentList(studentScore.getExam());

        Assertions.assertEquals(1, failStudentResponses.size());

        Double getAvgScore = new MyCalculator()
                .add(studentScore.getKorScore().doubleValue())
                .add(studentScore.getMathScore().doubleValue())
                .add(studentScore.getEnglishScore().doubleValue())
                .divide(3.0)
                .getResult();

        Assertions.assertEquals(studentScore.getStudentName(), failStudentResponses.get(0).getStudentName());
        Assertions.assertEquals(getAvgScore, failStudentResponses.get(0).getAvgScore());
    }


}
