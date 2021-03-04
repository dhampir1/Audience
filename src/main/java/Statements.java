import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Statements {

    ArrayList<Statement> statementList = new ArrayList<>();

    public void generateAudienceReport (Path inputPath){

        try {
            statementList.clear();
            readStatements(inputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Calendar c1 =Calendar.getInstance();
        Collections.sort(statementList);
        Calendar c2 =Calendar.getInstance();
        System.out.println(c2.getTimeInMillis()-c1.getTimeInMillis());

        calculateOutput(statementList);

    }

    private void calculateOutput(ArrayList<Statement> list){
        ProgressBarBuilder pbb = new ProgressBarBuilder();

        Path outputPath = Paths.get("src\\main\\java\\resources\\output.txt");

        Integer homeNumber = null;
        long duration=0;
        LocalDateTime endTime;

//        StringBuilder stringBuilder = new StringBuilder();
        try (ProgressBar pb = new ProgressBar("Calculate", list.size())) {
            FileOutputStream fileOutputStream = new FileOutputStream("output2.txt");
            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.println("HomeNo|Channel|StartTime|Activity|EndTime|Duration");
            for (int i = 0; i < list.size(); i++) {
                Statement statement1 = list.get(i);

                if (i < list.size() - 1) {
                    endTime = list.get(i + 1).getStartTime();
                    homeNumber = list.get(i + 1).getHomeNumber();
                } else {
                    endTime = statement1.getStartTime().plusDays(1).withHour(0).withMinute(0).withSecond(0);
                    homeNumber = statement1.getHomeNumber();
                }

                if (statement1.getHomeNumber().equals(homeNumber)) {
                    if (isSameDay(statement1.getStartTime(), endTime)) {
                        duration = ChronoUnit.SECONDS.between(statement1.getStartTime(), endTime);
                        OutputStatement outputStatement = new OutputStatement(homeNumber, statement1.getChannel(), statement1.getStartTime(), endTime, statement1.getActivity(), duration);
                        printWriter.println(outputStatement);
                    } else {
                        endTime = statement1.getStartTime().plusDays(1).withHour(0).withMinute(0).withSecond(0);
                        duration = ChronoUnit.SECONDS.between(statement1.getStartTime(), endTime);
                        OutputStatement outputStatement = new OutputStatement(homeNumber, statement1.getChannel(), statement1.getStartTime(), endTime, statement1.getActivity(), duration);
                        printWriter.println(outputStatement);
                    }
                } else {
                    endTime = statement1.getStartTime().plusDays(1).withHour(0).withMinute(0).withSecond(0);
                    duration = ChronoUnit.SECONDS.between(statement1.getStartTime(), endTime);
                    OutputStatement outputStatement = new OutputStatement(statement1.getHomeNumber(), statement1.getChannel(), statement1.getStartTime(), endTime, statement1.getActivity(), duration);
                    printWriter.println(outputStatement);
                }
                pb.step();
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("finish");


    }

   private boolean isSameDay(LocalDateTime date1, LocalDateTime date2) {
        return date1.toLocalDate().equals(date2.toLocalDate());
    }

    private void readStatements(Path inputPath) throws IOException {
        Calendar c1 =Calendar.getInstance();
        List<String> lines = Files.readAllLines(inputPath.toAbsolutePath(), UTF_8);
//        List<Statement> statements = new ArrayList<Statement>();
        lines.stream().skip(1).forEach(
                line->{
                    String[] fields = line.split("\\|");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                    LocalDateTime dateTime = LocalDateTime.parse(fields[2], formatter);
                    statementList.add(new Statement(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), dateTime, fields[3]));
                }
        );
        Calendar c3 =Calendar.getInstance();
        System.out.println(c3.getTimeInMillis()-c1.getTimeInMillis());
    }
}
