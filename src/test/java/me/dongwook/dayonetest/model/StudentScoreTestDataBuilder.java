package me.dongwook.dayonetest.model;

public class StudentScoreTestDataBuilder {

    public static StudentScore.StudentScoreBuilder passed() {
        return StudentScore.builder()
                .korScore(70)
                .englishScore(80)
                .mathScore(70)
                .studentName("defaultName")
                .exam("defaultExam");
    }

    public static StudentScore.StudentScoreBuilder failed() {
        return StudentScore.builder()
                .korScore(40)
                .englishScore(50)
                .mathScore(30)
                .studentName("defaultName")
                .exam("defaultExam");
    }


}
