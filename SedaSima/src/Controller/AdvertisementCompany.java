package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Model.*;
import Model.AdCompany;
import Model.Ad;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AdvertisementCompany {

    @FXML
    TextField adName;

    @FXML
    TextField timeField;

    @FXML
    TextField durationField;

    @FXML
    TextField channelField;

    @FXML
    TextField chargeField;
    @FXML
    TextField userNameField,visibleField;

    @FXML
    PasswordField PasswordField;

    @FXML
    TextField namefield;

    @FXML
    TextField balancefield;
    @FXML
    TextField LoginUserField;

    @FXML
    PasswordField LoginPassField;
    @FXML
    Label loginLabel;
    static int t;

    public void signUp(javafx.event.ActionEvent actionEvent) {
        try {
            AdCompany company = new AdCompany(Long.parseLong(balancefield.getText()), userNameField.getText()
                    , PasswordField.getText(), namefield.getText());
            save.adCompanies.add(company);
        } catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "please input valid number");
            alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "now you can Login");
        alert.showAndWait();
    }

    public void login(javafx.event.ActionEvent actionEvent) throws IOException {
        for (int i = 0; i < save.adCompanies.size(); i++) {
            if (LoginPassField.isVisible()){
            if (LoginUserField.getText().equals(save.adCompanies.get(i).getUserName()) &&
                    LoginPassField.getText().equals(save.adCompanies.get(i).getPassword())) {
                t = i;
                new PageLoader().load("..\\view\\companyPanel.fxml");
            } else
                loginLabel.setVisible(true);
        }
        else {
                if (LoginUserField.getText().equals(save.adCompanies.get(i).getUserName()) &&
                        visibleField.getText().equals(save.adCompanies.get(i).getPassword())) {
                    t = i;
                    new PageLoader().load("..\\view\\companyPanel.fxml");
                } else
                    loginLabel.setVisible(true);
        }
        }
    }

    public void goToLoginPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("..\\view\\AdCompanyLogin.fxml");
    }

    public void charge(ActionEvent actionEvent) {
        try {
            save.adCompanies.get(t).setBalance(save.adCompanies.get(t).getBalance() + Long.
                    parseLong(chargeField.getText()));
        } catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "please input valid amount!");
            alert.showAndWait();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "you've charge it !" + "\n"
                + "you have now " + save.adCompanies.get(t).getBalance() + " tooman");
        alert.showAndWait();
    }

    public boolean hasIntersection(Ad program) {
        AtomicBoolean tmp = new AtomicBoolean(true);
        String hr = program.getStartTime().substring(0, 2);
        String min = program.getStartTime().substring(3, 5);
        Integer time = Integer.parseInt(hr) * 60 + Integer.parseInt(min);
        save.channelManagers.get(t).getChannel().getPrograms().forEach(program1 -> {
            if (tmp.get()) {
                String newHr = program1.getStartTime().substring(0, 2);
                String newMin = program1.getStartTime().substring(3, 5);
                Integer newTime = Integer.parseInt(newHr) * 60 + Integer.parseInt(newMin);
                if (time.equals(newTime)) {
                    tmp.set(false);
                }
                if (time > newTime && time - newTime < Integer.parseInt(program1.getDuration())) {
                    tmp.set(false);
                } else if (newTime > time && newTime - time < Integer.parseInt(program.getDuration())) {
                    tmp.set(false);
                }
            }
        });
        return tmp.get();
    }

    public void request(ActionEvent actionEvent) {
        for (int i = 0; i < save.channelManagers.size(); i++) {
            if (channelField.getText().equals(save.channelManagers.get(i).getChannel().getName())) {
                Ad ad = new Ad(save.channelManagers.get(i).getChannel(), adName.getText(),
                        timeField.getText(), durationField.getText());
                if (hasIntersection(ad)) {
                    save.adCompanies.get(t).getAdList().add(ad);
                    save.channelManagers.get(i).getChannel().getAds().add(ad);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "time intersection error !");
                    alert.showAndWait();
                }
                break;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "the channel does not exist !");
                alert.showAndWait();
            }
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("..\\view\\CEOLogin.fxml");
    }

    public void undo(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("..\\view\\CEOLogin.fxml");
    }

    public void showPass(MouseEvent mouseEvent) {
        if (!visibleField.isVisible()) {
            visibleField.setText(LoginPassField.getText());
            LoginPassField.setVisible(false);
            visibleField.setVisible(true);
        } else {
            LoginPassField.setText(visibleField.getText());
            LoginPassField.setVisible(true);
            visibleField.setVisible(false);
        }
    }
}

