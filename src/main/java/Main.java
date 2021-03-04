import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

//        Path outputPath = Paths.get("src\\main\\resources\\StatementsDescending.txt");
//        InputStatementGenerator generator =new InputStatementGenerator();
//        generator.generate(5000000, outputPath);

        Calendar start = Calendar.getInstance();
        Path inputPath = Paths.get("src\\main\\resources\\StatementsDescending.txt");
        Statements statements = new Statements();
        statements.generateAudienceReport(inputPath);

        Calendar end = Calendar.getInstance();
        System.out.println(end.getTimeInMillis()-start.getTimeInMillis());

    }
}
