package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;

public class CEOLoginController {
    @FXML
    TextField userfield;
    @FXML
    PasswordField passfield;
    @FXML
    Label label;
    @FXML
    TextField visiblepass;


    public void Login(ActionEvent actionEvent) throws IOException {
        if (passfield.isVisible()) {
            if (userfield.getText().equals("12") && passfield.getText().equals("12")) {
                new PageLoader().load("..\\view\\CEOPanel.fxml");

            } else
                label.setVisible(true);
        } else {
            if (userfield.getText().equals("12") && visiblepass.getText().equals("12")) {
                new PageLoader().load("..\\view\\CEOPanel.fxml");
            } else
                label.setVisible(true);
        }
    }

    public void showPass(MouseEvent mouseEvent) {
        if (!visiblepass.isVisible()) {
            visiblepass.setText(passfield.getText());
            passfield.setVisible(false);
            visiblepass.setVisible(true);
        } else {
            passfield.setText(visiblepass.getText());
            passfield.setVisible(true);
            visiblepass.setVisible(false);
        }
    }

    public void guest(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("..\\view\\guestPanel.fxml");
    }

    public void manager(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("..\\view\\channelManagerLogin.fxml");
    }

    public void company(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("..\\view\\AdCompanySignUpPage.fxml");
    }

    public void guide(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("..\\view\\guide.fxml");
    }

    public void home(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("..\\view\\CEOLogin.fxml");
    }
}
