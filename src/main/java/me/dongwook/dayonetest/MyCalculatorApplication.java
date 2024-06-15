package me.dongwook.dayonetest;

public class MyCalculatorApplication {

    public static void main(String[] args) {
        MyCalculator myCalculator = new MyCalculator();

        myCalculator.add(10.0); // 10

        myCalculator.minus(2.0); // 8

        myCalculator.multuplu(2.0); // 16

        myCalculator.divide(2.0); // 8

        System.out.println(myCalculator.getResult());

    }
}
