package me.dongwook.dayonetest.service;

import me.dongwook.dayonetest.model.StudentScore;
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
    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 이상인 경우")
    void saveScoreMockTest() {

        // given : 평균 점수 60점 이상
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository, studentPassRepository, studentFailRepository
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
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 이하인 경우")
    void saveScoreMockTest2() {
        // given
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository, studentPassRepository, studentFailRepository
        );

        String givenStudentName = "dongwook";
        String givenExam = "testexam";
        Integer givenKoreanScore = 60;
        Integer givenEnglishScore = 40;
        Integer givenMathScore = 30;

        // when
        studentScoreService.saveScore(
                givenStudentName, givenExam, givenKoreanScore, givenEnglishScore, givenMathScore
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());
        Mockito.verify(studentFailRepository, Mockito.times(1)).save(Mockito.any());


    }

}
