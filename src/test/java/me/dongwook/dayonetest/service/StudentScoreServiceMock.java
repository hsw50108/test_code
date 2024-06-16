package me.dongwook.dayonetest.service;

import me.dongwook.dayonetest.repository.StudentFailRepository;
import me.dongwook.dayonetest.repository.StudentPassRepository;
import me.dongwook.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StudentScoreServiceMock {


    @Test
    @DisplayName("mock test")
    void firstSaveScoreMockTest() {
        // given
        StudentScoreService studentScoreService = new StudentScoreService(
                Mockito.mock(StudentScoreRepository.class),
                Mockito.mock(StudentPassRepository.class),
                Mockito.mock(StudentFailRepository.class)
        );
        String givenStudentName = "dongwook";
        String givenExam = "testexam";
        Integer givenKoreanScore = 80;
        Integer givenEnglishScore = 60;
        Integer givenMathScore = 50;

        // when
        studentScoreService.saveScore(
                givenStudentName, givenExam, givenKoreanScore, givenEnglishScore, givenMathScore
        );

        // then




    }

}
