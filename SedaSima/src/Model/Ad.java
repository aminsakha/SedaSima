package Model;

public class Ad {
    private boolean isAccept;
    private Channel channel;
    private String name, startTime, duration;

    public Ad(Channel channel, String name, String startTime, String duration) {
        this.channel = channel;
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public String getStartTime() {
        System.out.println("hello");
        return startTime;
    }
}
