package Model;

public class News extends Program {
    String presenter;


    public News(String name, String category, String date, String duration, String startTime, String repStart, String presenter) {
        super(name, category, date, duration, startTime, repStart);
        this.presenter = presenter;
    }
}
