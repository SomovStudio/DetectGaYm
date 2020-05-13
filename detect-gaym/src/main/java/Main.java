import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        System.out.println("|=================================================");
        System.out.println("| Detect events: Google Analytics & Yandex Metrika");
        System.out.println("| Author: Somov Studio (version 1.0)");
        System.out.println("|=================================================");
        TestCase.execute("\\tests\\test1.json");
        //TestCase.execute(args[0]);
    }
}
