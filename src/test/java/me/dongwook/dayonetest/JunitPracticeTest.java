package me.dongwook.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JunitPracticeTest {

    @Test
    void assertEqualsTest() {

        String expect = "SomeThing";
        String actual = "SomeThing";

        Assertions.assertEquals(expect, actual);
    }

    @Test
    void assertNotEqualsTest() {

        String expect = "SomeThing";
        String actual = "SomeThing!";

        Assertions.assertNotEquals(expect, actual);
    }

    @Test
    void assertTrueTest() {
        Integer a = 10;
        Integer b = 10;

        Assertions.assertTrue(a.equals(b));
    }

    @Test
    void assertFalseTest() {
        Integer a = 10;
        Integer b = 40;

        Assertions.assertFalse(a.equals(b));

    }

    @Test
    void assertThrowsTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("임의로 발생시킨 에러");
        });
    }

    @Test
    void assertNotnullTest() {
        String value = "Hello";
        Assertions.assertNotNull(value);
    }

    @Test
    void assertNullTest() {
        String value = null;
        Assertions.assertNull(value);
    }

    @Test
    void assertIterableEqualsTest() {
        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);

        Assertions.assertIterableEquals(list1, list2);
    }

    @Test
    void assertAllTest() {
        String expect = "SomeThing";
        String actual = "SomeThing";


        Integer a = 10;
        Integer b = 10;


        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);


        Assertions.assertAll("Assert All", List.of(
                () -> {
                    Assertions.assertEquals(expect, actual);
                },
                () -> {
                    Assertions.assertTrue(a.equals(b));
                },
                () -> {
                    Assertions.assertIterableEquals(list1, list2);
                }
        ));
    }

}
