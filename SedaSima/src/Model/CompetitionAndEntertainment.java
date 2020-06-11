package Model;

public class CompetitionAndEntertainment extends Program {
    String presenter;

    public CompetitionAndEntertainment(String name, String category, String date, String duration, String startTime, String repStart, String presenter) {
        super(name, category, date, duration, startTime, repStart);
        this.presenter = presenter;
    }
}
