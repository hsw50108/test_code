package me.dongwook.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyCalculatorTest {

    @Test
    void addTest() {
        // AAA 패턴

        // Arrange - 준비
        MyCalculator myCalculator = new MyCalculator();
        // Act - 행동
        myCalculator.add(10.0);
        // Assert - 단언/검증
        Assertions.assertEquals(10.0, myCalculator.getResult());
    }

    @Test
    void minusTest() {
        // GWT 패턴
        // Given - 준비
        MyCalculator myCalculator = new MyCalculator(10.0);

        // When - 행동/연산
        myCalculator.minus(5.0);

        // Then - 검증/비교/단언
        Assertions.assertEquals(5.0, myCalculator.getResult());
    }

    @Test
    void multiplyTest() {
        MyCalculator myCalculator = new MyCalculator(10.0);
        myCalculator.multuplu(2.0);
        Assertions.assertEquals(20.0, myCalculator.getResult());
    }

    @Test
    void divideTest() {
        MyCalculator myCalculator = new MyCalculator(10.0);
        myCalculator.divide(2.0);
        Assertions.assertEquals(5.0, myCalculator.getResult());
    }

    @Test
    void complicatedCalculateTest() {
        // given
        MyCalculator myCalculator = new MyCalculator(0.0);


        // when
        Double result = myCalculator
                .add(10.0)
                .minus(4.0)
                .multuplu(2.0)
                .divide(3.0)
                .getResult();

        // then
        Assertions.assertEquals(4.0, result);
    }

    @Test
    void divideZeroTest() {
        // given
        MyCalculator myCalculator = new MyCalculator(10.0);

        // when
        // then
        Assertions.assertThrows(MyCalculator.ZeroDivisionException.class, () -> myCalculator.divide(0.0));
    }

}