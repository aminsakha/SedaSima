package Model;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    public Channel(String name, int coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    private String name;
    private ChannelManager manager;

    public String getName() {
        return name;
    }

    public ChannelManager getManager() {
        return manager;
    }

    public void setManager(ChannelManager manager) {
        this.manager = manager;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public List<Ad> getAds() {
        return ads;
    }

    private List<Ad> ads = new ArrayList<>();
    private List<Program> programs = new ArrayList<>();
    private List<String> programsNames = new ArrayList<>();

    public List<String> getProgramsNames() {
        return programsNames;
    }

    private long coefficient;

    public long getCoefficient() {
        return coefficient;
    }
}
