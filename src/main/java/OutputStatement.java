import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OutputStatement {
    private Integer homeNumber;
    private Integer channel;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String activity;
    private long duration;

    public OutputStatement(Integer homeNumber, Integer channel, LocalDateTime startTime, LocalDateTime endTime, String activity, long duration) {
        this.homeNumber = homeNumber;
        this.channel = channel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
        this.duration = duration;
    }
    @Override
    public String toString() {
        String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        if(endTime.getSecond()!=0){
//            endTime = endTime.minusSeconds(1);
//        }else if (endTime.getSecond() == 0 && endTime.getMinute() != 0){
//            endTime = endTime.minusMinutes(1);
//        }else if (endTime.getSecond() == 0 && endTime.getMinute() == 0 && endTime.getHour() !=0) {
//            endTime = endTime.minusHours(1);
//        }
        String formattedEndTime = endTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return homeNumber +
                "|" + channel +
                "|" + formattedStartTime +
                "|" + activity +
                "|" + formattedEndTime +
                "|" + duration;
    }
}
