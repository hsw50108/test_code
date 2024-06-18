package me.dongwook.dayonetest.model;

public class StudentScoreFixture {

    public static StudentScore passed() {
        return StudentScore
                .builder()
                .exam("default exam")
                .studentName("default student")
                .korScore(70)
                .mathScore(80)
                .englishScore(60)
                .build();
    }


    public static StudentScore failed() {
        return StudentScore
                .builder()
                .exam("default exam")
                .studentName("default student")
                .korScore(40)
                .mathScore(30)
                .englishScore(50)
                .build();
    }


}
