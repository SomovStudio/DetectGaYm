package com.detect.gaym;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("|=================================================");
        System.out.println("| Detect events: Google Analytics & Yandex Metrika");
        System.out.println("| 2020 © Somov Evgeniy (version 1.0.0)");
        System.out.println("|=================================================");

        String argumentFileName = "";
        String argumentFileEncoding = "";
        if(args.length > 1){
            argumentFileName = args[1];
            argumentFileEncoding = args[0];
        }else{
            argumentFileName = args[0];
        }
        if(argumentFileName.lastIndexOf("json") >= 0) TestCase.execute(argumentFileName, argumentFileEncoding);
        else TestCase.executeAll(argumentFileName, argumentFileEncoding);
    }
}
