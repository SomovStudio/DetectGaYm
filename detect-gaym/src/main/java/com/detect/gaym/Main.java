package com.detect.gaym;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("|=================================================");
        System.out.println("| Detect Google Analytics & Yandex Metrika");
        System.out.println("| Latest stable version: 1.0.1 (beta 1.0.1.1)");
        System.out.println("| © Somov Evgeniy, 2020. All Rights Reserved.");
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
