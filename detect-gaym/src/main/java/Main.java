import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("|=================================================");
        System.out.println("| Detect events: Google Analytics & Yandex Metrika");
        System.out.println("| Author: Somov Studio (version 2.0.0)");
        System.out.println("|=================================================");
        //TestCase.execute("\\tests\\test1.json");
        //TestCase.executeAll("tests\\");

        String argument = args[0];
        if(argument.lastIndexOf("json") >= 0) TestCase.execute(argument);
        else TestCase.executeAll(argument);
    }
}
