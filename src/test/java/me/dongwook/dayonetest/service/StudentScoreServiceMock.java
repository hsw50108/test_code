package me.dongwook.dayonetest.service;

import me.dongwook.dayonetest.controller.response.ExamFailStudentResponse;
import me.dongwook.dayonetest.controller.response.ExamPassStudentResponse;
import me.dongwook.dayonetest.model.*;
import me.dongwook.dayonetest.repository.StudentFailRepository;
import me.dongwook.dayonetest.repository.StudentPassRepository;
import me.dongwook.dayonetest.repository.StudentScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

public class StudentScoreServiceMock {

    private StudentScoreService studentScoreService;
    private StudentScoreRepository studentScoreRepository;
    private StudentPassRepository studentPassRepository;
    private StudentFailRepository studentFailRepository;

    @BeforeEach
    void beforeEach() {
        studentScoreRepository = Mockito.mock(StudentScoreRepository.class);
        studentPassRepository = Mockito.mock(StudentPassRepository.class);
        studentFailRepository = Mockito.mock(StudentFailRepository.class);

        studentScoreService = new StudentScoreService(
                studentScoreRepository,
                studentPassRepository,
                studentFailRepository);
    }


    @Test
    @DisplayName("mock test")
    void firstSaveScoreMockTest() {
        // given
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
        StudentScore expectStudentScore = StudentScoreTestDataBuilder.passed()
                .build();

        StudentPass expectStudentPass = StudentPassFixture.create(expectStudentScore);

        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentPass> studentPassArgumentCaptor = ArgumentCaptor.forClass(StudentPass.class);

        // when
        studentScoreService.saveScore(
                expectStudentScore.getStudentName(),
                expectStudentScore.getExam(),
                expectStudentScore.getKorScore(),
                expectStudentScore.getEnglishScore(),
                expectStudentScore.getMathScore()
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
        Assertions.assertEquals(expectStudentPass.getStudentName(), capturedStudentPass.getStudentName());
        Assertions.assertEquals(expectStudentPass.getExam(), capturedStudentPass.getExam());
        Assertions.assertEquals(expectStudentPass.getAvgScore(), capturedStudentPass.getAvgScore());

        Mockito.verify(studentFailRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    @DisplayName("성적 저장 로직 검증 / 60점 미만인 경우")
    void saveScoreMockTest2() {
        // given
        ArgumentCaptor<StudentScore> studentScoreArgumentCaptor = ArgumentCaptor.forClass(StudentScore.class);
        ArgumentCaptor<StudentFail> studentFailArgumentCaptor = ArgumentCaptor.forClass(StudentFail.class);

//        String givenStudentName = "dongwook";
//        String givenExam = "testexam";
//        Integer givenKoreanScore = 60;
//        Integer givenEnglishScore = 40;
//        Integer givenMathScore = 30;

        StudentScore expectStudentScore = StudentScoreFixture.failed();

//        StudentScore expectStudentScore = StudentScore.builder()
//                .studentName(givenStudentName)
//                .exam(givenExam)
//                .korScore(givenKoreanScore)
//                .englishScore(givenEnglishScore)
//                .mathScore(givenMathScore)
//                .build();


        StudentFail expectStudentFail = StudentFailFixture.create(expectStudentScore);
//        StudentFail expectStudentFail = StudentFail.builder()
//                .studentName(expectStudentScore.getStudentName())
//                .exam(expectStudentScore.getExam())
//                .avgScore(
//                        new MyCalculator(0.0)
//                                .add(expectStudentScore.getKorScore().doubleValue())
//                                .add(expectStudentScore.getEnglishScore().doubleValue())
//                                .add(expectStudentScore.getMathScore().doubleValue())
//                                .divide(3.0)
//                                .getResult()
//                ).build();


        // when
        studentScoreService.saveScore(
                expectStudentScore.getStudentName(),
                expectStudentScore.getExam(),
                expectStudentScore.getKorScore(),
                expectStudentScore.getEnglishScore(),
                expectStudentScore.getMathScore()
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
        String givenTestExam = "testexam";

        StudentPass expectedStudent1 = StudentPassFixture.create("dongwook", givenTestExam);
        StudentPass expectedStudent2 = StudentPassFixture.create("dongwook2", givenTestExam);
        StudentPass notExpectedStudent3 = StudentPassFixture.create("another name", "another exam");

        Mockito.when(studentPassRepository.findAll()).thenReturn(List.of(
                expectedStudent1, expectedStudent2, notExpectedStudent3
        ));


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
        String givenTestExam = "testexam";

        StudentFail expectedStudent1 = StudentFailFixture.create("dongwook", givenTestExam);
        StudentFail expectedStudent2 = StudentFailFixture.create("dongwook2", givenTestExam);
        StudentFail notExpectedStudent3 = StudentFailFixture.create("another name", "another test exam");


        Mockito.when(studentFailRepository.findAll()).thenReturn(List.of(
                expectedStudent1, expectedStudent2, notExpectedStudent3
        ));


        // when
        List<ExamFailStudentResponse> expectResponse = Stream.of(expectedStudent1, expectedStudent2)
                .map((pass) -> new ExamFailStudentResponse(pass.getStudentName(), pass.getAvgScore())).toList();

        List<ExamFailStudentResponse> responses = studentScoreService.getFailStudentList(givenTestExam);

        Assertions.assertIterableEquals(expectResponse, responses);

    }


}
