package me.dongwook.dayonetest.service;

import me.dongwook.dayonetest.MyCalculator;
import me.dongwook.dayonetest.controller.response.ExamFailStudentResponse;
import me.dongwook.dayonetest.controller.response.ExamPassStudentResponse;
import me.dongwook.dayonetest.model.StudentFail;
import me.dongwook.dayonetest.model.StudentPass;
import me.dongwook.dayonetest.model.StudentScore;
import me.dongwook.dayonetest.repository.StudentFailRepository;
import me.dongwook.dayonetest.repository.StudentPassRepository;
import me.dongwook.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

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

        StudentScore expectStudentScore = StudentScore
                .builder()
                .studentName(givenStudentName)
                .exam(givenExam)
                .korScore(givenKoreanScore)
                .englishScore(givenEnglishScore)
                .mathScore(givenMathScore)
                .build();

        StudentPass expextStudentPass = StudentPass
                .builder()
                .studentName(givenStudentName)
                .exam(givenExam)
                .avgScore(
                        new MyCalculator(0.0)
                                .add(givenKoreanScore.doubleValue())
                                .add(givenEnglishScore.doubleValue())
                                .add(givenMathScore.doubleValue())
                                .divide(3.0)
                                .getResult()
                )
                .build();

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass.class);

        // when
        studentScoreService.saveScore(
                givenStudentName, givenExam, givenKoreanScore, givenEnglishScore, givenMathScore
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture());
        StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();

        Assertions.assertEquals(expectStudentScore.getStudentName(), capturedStudentScore.getStudentName());
        Assertions.assertEquals(expectStudentScore.getExam(), capturedStudentScore.getExam());
        Assertions.assertEquals(expectStudentScore.getKorScore(), capturedStudentScore.getKorScore());
        Assertions.assertEquals(expectStudentScore.getMathScore(), capturedStudentScore.getMathScore());
        Assertions.assertEquals(expectStudentScore.getEnglishScore(), capturedStudentScore.getEnglishScore());

        Mockito.verify(studentPassRepository, Mockito.times(1)).save(studentPassArgumentCaptor.capture());
        StudentPass capturedStudentPass = studentPassArgumentCaptor.getValue();
        Assertions.assertEquals(expextStudentPass.getStudentName(), capturedStudentPass.getStudentName());
        Assertions.assertEquals(expextStudentPass.getExam(), capturedStudentPass.getExam());
        Assertions.assertEquals(expextStudentPass.getAvgScore(), capturedStudentPass.getAvgScore());


        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 미만인 경우")
    void saveScoreMockTest2() {
        // given
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository, studentPassRepository, studentFailRepository
        );

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentFail> studentFailArgumentCaptor = ArgumentCaptor.forClass(StudentFail.class);

        String givenStudentName = "dongwook";
        String givenExam = "testexam";
        Integer givenKoreanScore = 60;
        Integer givenEnglishScore = 40;
        Integer givenMathScore = 30;

        StudentScore expectStudentScore = StudentScore.builder()
                .studentName(givenStudentName)
                .exam(givenExam)
                .korScore(givenKoreanScore)
                .englishScore(givenEnglishScore)
                .mathScore(givenMathScore)
                .build();

        StudentFail expectStudentFail = StudentFail.builder()
                .studentName(givenStudentName)
                .exam(givenExam)
                .avgScore(
                        new MyCalculator(0.0)
                                .add(givenKoreanScore.doubleValue())
                                .add(givenEnglishScore.doubleValue())
                                .add(givenMathScore.doubleValue())
                                .divide(3.0)
                                .getResult()
                ).build();


        // when
        studentScoreService.saveScore(
                givenStudentName, givenExam, givenKoreanScore, givenEnglishScore, givenMathScore
        );

        // then
        Mockito.verify(studentScoreRepository, Mockito.times(1)).save(studentScoreArgumentCaptor.capture());
        StudentScore capturedStudentScore = studentScoreArgumentCaptor.getValue();

        Assertions.assertEquals(expectStudentScore.getStudentName(), capturedStudentScore.getStudentName());
        Assertions.assertEquals(expectStudentScore.getExam(), capturedStudentScore.getExam());
        Assertions.assertEquals(expectStudentScore.getKorScore(), capturedStudentScore.getKorScore());
        Assertions.assertEquals(expectStudentScore.getMathScore(), capturedStudentScore.getMathScore());
        Assertions.assertEquals(expectStudentScore.getEnglishScore(), capturedStudentScore.getEnglishScore());


        Mockito.verify(studentPassRepository, Mockito.times(0)).save(Mockito.any());

        Mockito.verify(studentFailRepository, Mockito.times(1)).save(studentFailArgumentCaptor.capture());
        StudentFail capturedStudentFail = studentFailArgumentCaptor.getValue();

        Assertions.assertEquals(expectStudentFail.getStudentName(), capturedStudentFail.getStudentName());
        Assertions.assertEquals(expectStudentFail.getExam(), capturedStudentFail.getExam());
        Assertions.assertEquals(expectStudentFail.getAvgScore(), capturedStudentFail.getAvgScore());

    }

    @Test
    @DisplayName("합격자 명단 가져오기")
    void getPassStudentListTest() {
        // given
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository, studentPassRepository, studentFailRepository
        );

        StudentPass expectedStudent1 = StudentPass.builder().id(1L).studentName("dongwook").exam("testexam").avgScore(70.0).build();
        StudentPass expectedStudent2 = StudentPass.builder().id(2L).studentName("dongwook2").exam("testexam").avgScore(80.0).build();
        StudentPass notExpectedStudent3 = StudentPass.builder().id(3L).studentName("dongwook3").exam("secondexam").avgScore(90.0).build();

        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
                expectedStudent1, expectedStudent2, notExpectedStudent3
        ));

        String givenTestExam = "testexam";

        // when
        List<ExamPassStudentResponse> expectResponse = Stream.of(expectedStudent1, expectedStudent2)
                .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore())).toList();

        List<ExamPassStudentResponse> responses = studentScoreService.getPassStudentList(givenTestExam);
//        passStudentList.forEach((response) -> System.out.println(response.getStudentName() + ' ' + response.getAvgScore()));

        Assertions.assertIterableEquals(expectResponse, responses);

        expectResponse.forEach((response)
                -> System.out.println(response.getStudentName() + " " + response.getAvgScore())
        );

        System.out.println("---------------");

        responses.forEach((response)
                -> System.out.println(response.getStudentName() + " " + response.getAvgScore())
        );

    }

    @Test
    @DisplayName("불합격자 명단 가져오기")
    void getFailStudentListTest() {
        // given
        StudentScoreRepository studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        StudentPassRepository studentPassRepository = Mockito.mock(StudentPassRepository.class);
        StudentFailRepository studentFailRepository = Mockito.mock(StudentFailRepository.class);

        StudentScoreService studentScoreService = new StudentScoreService(
                studentScoreRepository, studentPassRepository, studentFailRepository
        );

        StudentFail expectedStudent1 = StudentFail.builder().id(1L).studentName("dongwook").exam("testexam").avgScore(40.0).build();
        StudentFail expectedStudent2 = StudentFail.builder().id(2L).studentName("dongwook2").exam("testexam").avgScore(50.0).build();
        StudentFail notExpectedStudent3 = StudentFail.builder().id(3L).studentName("dongwook3").exam("secondexam").avgScore(90.0).build();


        Mockito.when(studentFailRepository.findAll()).thenReturn(List.of(
                expectedStudent1, expectedStudent2, notExpectedStudent3
        ));

        String givenTestExam = "testexam";

        // when
        List<ExamFailStudentResponse> expectResponse = Stream.of(expectedStudent1, expectedStudent2)
                .map((pass) -> new ExamFailStudentResponse(pass.getStudentName(), pass.getAvgScore())).toList();

        List<ExamFailStudentResponse> responses = studentScoreService.getFailStudentList(givenTestExam);

        Assertions.assertIterableEquals(expectResponse, responses);

    }


}
