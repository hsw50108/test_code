package me.dongwook.dayonetest.service;

import lombok.RequiredArgsConstructor;
import me.dongwook.dayonetest.MyCalculator;
import me.dongwook.dayonetest.controller.response.ExamFailStudentResponse;
import me.dongwook.dayonetest.controller.response.ExamPassStudentResponse;
import me.dongwook.dayonetest.model.StudentFail;
import me.dongwook.dayonetest.model.StudentPass;
import me.dongwook.dayonetest.model.StudentScore;
import me.dongwook.dayonetest.repository.StudentFailRepository;
import me.dongwook.dayonetest.repository.StudentPassRepository;
import me.dongwook.dayonetest.repository.StudentScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentScoreService {

    private final StudentScoreRepository studentScoreRepository;

    private final StudentPassRepository studentPassRepository;

    private final StudentFailRepository studentFailRepository;

    public void saveScore(String studentName, String exam, Integer korScore, Integer englishScore, Integer mathScore) {

        StudentScore studentScore = StudentScore.builder()
                .studentName(studentName)
                .exam(exam)
                .korScore(korScore)
                .englishScore(englishScore)
                .mathScore(mathScore)
                .build();

        studentScoreRepository.save(studentScore);

        MyCalculator calculator = new MyCalculator(0.0);
        Double avgScore = calculator
                .add(korScore.doubleValue())
                .add(englishScore.doubleValue())
                .add(mathScore.doubleValue())
                .divide(3.0)
                .getResult();

        if (avgScore >= 60) {

            StudentPass studentPass = StudentPass.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build();

            studentPassRepository.save(studentPass);
        } else {
            StudentFail studentFail = StudentFail.builder()
                    .exam(exam)
                    .studentName(studentName)
                    .avgScore(avgScore)
                    .build();

            studentFailRepository.save(studentFail);
        }

    }

    public List<ExamPassStudentResponse> getPassStudentList(String exam) {
        List<StudentPass> studentPassList = studentPassRepository.findAll();

        return studentPassList
                .stream()
                .filter((pass) -> pass.getExam().equals(exam))
                .map((pass) -> new ExamPassStudentResponse(pass.getStudentName(), pass.getAvgScore()))
                .toList();
    }

    public List<ExamFailStudentResponse> getFailStudentList(String exam) {

        List<StudentFail> studentFailList = studentFailRepository.findAll();

        return studentFailList
                .stream()
                .filter((fail) -> fail.getExam().equals(exam))
                .map((fail) -> new ExamFailStudentResponse(fail.getStudentName(), fail.getAvgScore()))
                .toList();
    }


}
