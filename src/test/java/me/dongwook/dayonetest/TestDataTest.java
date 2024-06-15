package me.dongwook.dayonetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDataTest {

    @Test
    void testDataTest() {

        TestData testData = new TestData();

        testData.setName("dongwook");

        Assertions.assertEquals("dongwook", testData.getName());

    }

}
