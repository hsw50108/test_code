package me.dongwook.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MyCalculatorRepeatableTest {

    @DisplayName("덧셈을 단순 반복해서 테스트한다.")
    @RepeatedTest(10)
    void addTest() {
        MyCalculator myCalculator = new MyCalculator();
        myCalculator.add(10.0);
        Assertions.assertEquals(10.0, myCalculator.getResult());
    }

    @DisplayName("덧셈을 5번 파라미터를 넣어 반복해서 테스트한다.")
    @ParameterizedTest
    @MethodSource("parameterizedTest")
    void parameterizedTest(Double addValue, Double expectValue) {
        MyCalculator myCalculator = new MyCalculator(0.0);
        myCalculator.add(addValue);
        Assertions.assertEquals(expectValue, myCalculator.getResult());
    }

    public static Stream<Arguments> parameterizedTest() {
        return Stream.of(
                Arguments.of(10.0, 10.0),
                Arguments.of(20.0, 20.0),
                Arguments.of(30.0, 30.0),
                Arguments.of(40.0, 40.0),
                Arguments.of(50.0, 50.0)
        );
    }

    @DisplayName("MyCalculator 사칙연산 테스트를 파라미터를 넣어 2번 반복해서 테스트한다. ")
    @ParameterizedTest
    @MethodSource("complicatedCalculateTest")
    void parameterizedComplicatedCalculateTest(
            Double addValue,
            Double minusValue,
            Double divideValue,
            Double multiplyValue,
            Double resultValue) {

        MyCalculator myCalculator = new MyCalculator(0.0);

        Double result = myCalculator
                .add(addValue)
                .minus(minusValue)
                .multiply(multiplyValue)
                .divide(divideValue)
                .getResult();

        Assertions.assertEquals(resultValue, result);
    }

    public static Stream<Arguments> complicatedCalculateTest() {
        return Stream.of(
                Arguments.of(10.0, 4.0, 2.0, 6.0, 18.0),
                Arguments.of(10.0, 4.0, 2.0, 12.0, 36.0)
        );
    }


}
