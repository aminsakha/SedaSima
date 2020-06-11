package Model;

import java.util.ArrayList;
import java.util.List;

public class ChannelManager {
    private String userName, passWord;

    public Channel getChannel() {
        return channel;
    }

    private Channel channel;
    private List<Program> programs = new ArrayList<>();

    public ChannelManager(String userName, String passWord, Channel channel) {
        this.userName = userName;
        this.passWord = passWord;
        this.channel = channel;
    }


    public String getPassWord() {
        return passWord;
    }

    public String getUserName() {
        return userName;
    }
}
