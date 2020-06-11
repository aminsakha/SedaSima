package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChannelManagerController {
    static int t;
    @FXML
    TextField userfield, visibleField;
    @FXML
    PasswordField passfield;
    @FXML
    Label label;
    @FXML
    TextField namefield, repfield, startfield, durationfield, presenter, newstarttimefield, newreptimefield;
    @FXML
    DatePicker datefield, newdatefield;
    @FXML
    ChoiceBox<String> kindfield = new ChoiceBox<>();
    @FXML
    ListView<String> adlist = new ListView<>();
    @FXML
    ChoiceBox<String> choicefield2 = new ChoiceBox<>();
    @FXML
    ChoiceBox<String> choicefield3 = new ChoiceBox<>();
    ObservableList<String> kinds = FXCollections.observableArrayList("Film", "News",
            "Serial", "Competition & Entertainment");

    public boolean hasIntersection(Program program) {
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

    public void initialize() {
        kindfield.setItems(kinds);
        for (int i = 0; i < save.channelManagers.get(t).getChannel().getAds().size(); i++) {
            adlist.getItems().add(save.channelManagers.get(t).getChannel().getAds().get(i).getName());
        }
    }

    public void login(ActionEvent actionEvent) throws IOException {
        for (int i = 0; i < save.channelManagers.size(); i++) {
            if (passfield.isVisible()) {
                if (save.channelManagers.get(i).getUserName().equals(userfield.getText()) &&
                        save.channelManagers.get(i).getPassWord().equals(passfield.getText())) {
                    t = i;
                    new PageLoader().load("..\\view\\ChannelManagerPanel.fxml");
                } else
                    label.setVisible(true);
            } else {
                if (save.channelManagers.get(i).getUserName().equals(userfield.getText()) &&
                        save.channelManagers.get(i).getPassWord().equals(visibleField.getText())) {
                    t = i;
                    new PageLoader().load("..\\view\\ChannelManagerPanel.fxml");
                } else
                    label.setVisible(true);
            }
        }
    }

    public void makeProgram(ActionEvent actionEvent) {
        try {
            if (!namefield.getText().equals("")
                    && !durationfield.getText().equals("") &&
                    !startfield.getText().equals("") && !repfield.getText().equals("")) {
                String select = kindfield.getSelectionModel().getSelectedItem();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                switch (select) {
                    case "Film":
                    case "Serial":
                        Program filmOrSerial = new Program(namefield.getText(), select,
                                datefield.getValue().format(formatter),
                                durationfield.getText(), startfield.getText(), repfield.getText());
                        if (hasIntersection(filmOrSerial)) {
                            save.channelManagers.get(t).getChannel().getPrograms().add(filmOrSerial);
                            save.channelManagers.get(t).getChannel().getProgramsNames().add(filmOrSerial.getName());
                            save.choices2.setAll(save.channelManagers.get(t).getChannel().getProgramsNames());
                            choicefield2.setItems(save.choices2);
                            choicefield3.setItems(save.choices2);
                            namefield.setText(null);
                            durationfield.setText(null);
                            startfield.setText(null);
                            repfield.setText(null);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "it has time intersection");
                            alert.showAndWait();
                        }
                        break;
                    case "News":
                    case "Competition & Entertainment":
                        Program newsOrComp = new News(namefield.getText(), select, datefield
                                .getValue().format(formatter), durationfield.getText()
                                , startfield.getText(), repfield.getText(), presenter.getText());
                        if (hasIntersection(newsOrComp)) {
                            save.channelManagers.get(t).getChannel().getPrograms().add(newsOrComp);
                            save.channelManagers.get(t).getChannel().getProgramsNames().add(newsOrComp.getName());
                            save.choices2.setAll(save.channelManagers.get(t).getChannel().getProgramsNames());
                            choicefield2.setItems(save.choices2);
                            choicefield3.setItems(save.choices2);
                            namefield.setText(null);
                            durationfield.setText(null);
                            startfield.setText(null);
                            repfield.setText(null);
                            presenter.setText(null);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "it has time intersection");
                            alert.showAndWait();
                        }
                        break;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "you've forgot sth");
                alert.showAndWait();
            }
        } catch (NullPointerException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "you've forgot sth");
            alert.showAndWait();
        }

    }

    public void deleteProgram(ActionEvent actionEvent) {
        String select = choicefield2.getSelectionModel().getSelectedItem();
        for (int i = 0; i < save.channelManagers.get(t).getChannel().getPrograms().size(); i++) {
            if (select.equals(save.channelManagers.get(t).getChannel().getPrograms().get(i).getName())) {
                save.channelManagers.get(t).getChannel().getProgramsNames().remove(i);
                save.channelManagers.get(t).getChannel().getPrograms().remove(i);
                save.choices2.setAll(save.channelManagers.get(t).getChannel().getProgramsNames());
                choicefield2.setItems(save.choices2);
                choicefield3.setItems(save.choices2);
            }
        }
    }

    public void change(ActionEvent actionEvent) {
        String select = choicefield3.getSelectionModel().getSelectedItem();
        for (int i = 0; i < save.channelManagers.get(t).getChannel().getPrograms().size(); i++) {
            if (select.equals(save.channelManagers.get(t).getChannel()
                    .getPrograms().get(i).getName())) {
                if (newstarttimefield.isPressed()) {
                    save.channelManagers.get(t).getChannel().getPrograms().get(i).
                            setStartTime(newstarttimefield.getText());
                }
                if (newdatefield.isPressed()) {
                    save.channelManagers.get(t).getChannel().getPrograms().get(i)
                            .setDate(newstarttimefield.getText());
                }
                if (newreptimefield.isPressed()) {
                    save.channelManagers.get(t).getChannel().getPrograms().get(i)
                            .setRepStart(newstarttimefield.getText());
                }
            }
        }
    }

    public void Undo(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("..\\view\\CEOLogin.fxml");
    }

    public void back(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("..\\view\\CEOLogin.fxml");
    }

    public void accept(ActionEvent actionEvent) {
        String select = adlist.getSelectionModel().getSelectedItem();
        for (int j = 0; j < save.adCompanies.size(); j++) {
            for (int k = 0; k < save.adCompanies.get(j).getAdList().size(); k++) {
                if (save.adCompanies.get(j).getAdList().get(k).getName().equals(select) &&
                        save.adCompanies.get(j).getBalance() > save.channelManagers.get(t).
                                getChannel().getCoefficient() *
                                Double.parseDouble(save.adCompanies.get(j).getAdList().get(j).getDuration()) * 60) {
                    save.adCompanies.get(j).setBalance((int) (save.adCompanies.get(j).getBalance() -
                            save.channelManagers.get(t).
                                    getChannel().getCoefficient() *
                                    Double.parseDouble(save.adCompanies.get(j).getAdList().get(j).getDuration()) * 60));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "now it is accepted");
                    alert.showAndWait();
                }
            }
        }
    }

    public void reject(ActionEvent actionEvent) {
        System.err.println("rejected");
    }

    public void showPass(MouseEvent mouseEvent) {
        if (!visibleField.isVisible()) {
            visibleField.setText(passfield.getText());
            passfield.setVisible(false);
            visibleField.setVisible(true);
        } else {
            passfield.setText(visibleField.getText());
            passfield.setVisible(true);
            visibleField.setVisible(false);
        }
    }
}
