package Model;

public class Program {
    public Program(String name, String category, String date, String duration, String startTime, String repStart) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.duration = duration;
        this.startTime = startTime;
        this.repStart = repStart;
    }

    private String name,category;

    public String getCategory() {
        return category;
    }

    private int rate, votersCount;

    public void setRate(int rate) {
        this.rate = rate;
    }

    private String date, duration, startTime, repStart;

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getRate() {
        return rate;
    }

    public int getVotersCount() {
        return votersCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setRepStart(String repStart) {
        this.repStart = repStart;
    }
}
