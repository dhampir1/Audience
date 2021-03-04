import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Statement implements Comparable <Statement>{
    private Integer homeNumber;
    private Integer channel;
    private LocalDateTime startTime;
    private String activity;

    public Statement(Integer homeNumber, Integer channel, LocalDateTime startTime, String activity) {
        this.homeNumber = homeNumber;
        this.channel = channel;
        this.startTime = startTime;
        this.activity = activity;
    }

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
        this.homeNumber = homeNumber;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        String formattedStartTime = startTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return  homeNumber + "|" + channel + "|" + formattedStartTime + "|" + activity;
    }

    @Override
    public int compareTo(Statement o) {
//        return this.getHomeNumber().compareTo(o.getHomeNumber());
        int compareResult = Integer.compare(this.getHomeNumber(), o.getHomeNumber());
        if(compareResult == 0){
            compareResult = this.getStartTime().compareTo(o.getStartTime());
        }
        return compareResult;
    }
}
