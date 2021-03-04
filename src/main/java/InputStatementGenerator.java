import me.tongfei.progressbar.ProgressBar;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Random;


public class InputStatementGenerator {
    public void generate (int numberOfStatements, Path outputPath){
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = dateTime.minusDays(10);
        Random random = new Random();

        try (ProgressBar pb = new ProgressBar("Generate", numberOfStatements)){
            FileOutputStream fileOutputStream = new FileOutputStream(outputPath.toAbsolutePath().toString());
            PrintWriter printWriter = new PrintWriter(fileOutputStream);

           printWriter.println("HomeNo|Channel|StartTime|Activity");
            for (int i=0; i<numberOfStatements; i++){

                String activity;
                int a = random.nextInt(10);
                if (a>2){
                    activity = "Live";
                }else{
                    activity = "Playback";
                }
                dateTime = dateTime.minusSeconds(random.nextInt(10));
                Statement statement = new Statement(random.nextInt(50000)+1, random.nextInt(100) , dateTime, activity);
                printWriter.println(statement);
                pb.step();
            }
            printWriter.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}}
