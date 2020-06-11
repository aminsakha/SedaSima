package Model;

import java.util.ArrayList;
import java.util.List;

public class AdCompany {
    private long balance;
    private List<Ad> adList = new ArrayList<>();
    private String userName, password, companyName;

    public AdCompany(long balance, String userName, String password, String companyName) {
        this.balance = balance;
        this.userName = userName;
        this.password = password;
        this.companyName = companyName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

