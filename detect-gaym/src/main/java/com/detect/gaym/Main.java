package com.detect.gaym;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("|=================================================");
        System.out.println("| Detect events: Google Analytics & Yandex Metrika");
        System.out.println("| 2020 © Somov Studio (version 1.0.0)");
        System.out.println("|=================================================");

        String argument = args[0];
        if(argument.lastIndexOf("json") >= 0) TestCase.execute(argument);
        else TestCase.executeAll(argument);
    }
}
