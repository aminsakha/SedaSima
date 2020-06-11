package Controller;

import Model.AdCompany;
import Model.Channel;
import Model.ChannelManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class save {
    public static List<Channel> channels = new ArrayList<>();
    public static List<ChannelManager> channelManagers = new ArrayList<>();
    public static List<AdCompany> adCompanies = new ArrayList<>();
    public static List<String> names = new ArrayList<>();
    public static ObservableList<String> choices = FXCollections.observableArrayList();
    public static ObservableList<String> choices2 = FXCollections.observableArrayList();
}
