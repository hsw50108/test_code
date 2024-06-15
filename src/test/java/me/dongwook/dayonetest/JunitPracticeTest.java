package me.dongwook.dayonetest;

import org.junit.jupiter.api.*;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JunitPracticeTest {

    @Test
    @DisplayName("assert equals 테스트")
    void assertEqualsTest() {

        String expect = "SomeThing";
        String actual = "SomeThing";

        Assertions.assertEquals(expect, actual);
    }

    @Test
    @DisplayName("assert not equals 테스트")
    void assertNotEqualsTest() {

        String expect = "SomeThing";
        String actual = "SomeThing!";

        Assertions.assertNotEquals(expect, actual);
    }

    @Test
    @DisplayName("assert true 테스트")
    void assertTrueTest() {
        Integer a = 10;
        Integer b = 10;

        Assertions.assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("assert false 테스트")
    void assertFalseTest() {
        Integer a = 10;
        Integer b = 40;

        Assertions.assertFalse(a.equals(b));

    }

    @Test
    @DisplayName("assert throws 테스트")
    void assertThrowsTest() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("임의로 발생시킨 에러");
        });
    }

    @Test
    @DisplayName("assert not null 테스트")
    void assertNotnullTest() {
        String value = "Hello";
        Assertions.assertNotNull(value);
    }

    @Test
    @DisplayName("assert null 테스트")
    void assertNullTest() {
        String value = null;
        Assertions.assertNull(value);
    }

    @Test
    @DisplayName("assert assert Iterable Equals 테스트")
    void assertIterableEqualsTest() {
        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);

        Assertions.assertIterableEquals(list1, list2);
    }

    @Test
    @DisplayName("assert assertAll 테스트")
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
