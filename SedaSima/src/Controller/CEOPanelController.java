package Controller;

import Model.Channel;
import Model.ChannelManager;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CEOPanelController {

    @FXML
    TextField channelnamefield;
    @FXML
    TextField managernamefield;
    @FXML
    TextField managerpassfield;
    @FXML
    TextField pricefield;
    @FXML
    ChoiceBox<String> choicefield = new ChoiceBox<>();

    public void initialize() {
        choicefield.setItems(save.choices);
    }


    public void saveChannel(ActionEvent actionEvent) {
        try {
            Channel channel = new Channel(channelnamefield.getText(), Integer.parseInt(pricefield.getText()));
            save.channels.add(channel);
            save.names.add(channel.getName());
        } catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "please input valid amount");
            alert.showAndWait();
        }
        save.choices.setAll(save.names);
        choicefield.setItems(save.choices);
    }

    public void saveManager(ActionEvent actionEvent) {
        if (!managernamefield.getText().equals("") &&
                !managerpassfield.getText().equals("") &&
                !choicefield.getSelectionModel().getSelectedItem().equals("")) {
            String select = choicefield.getSelectionModel().getSelectedItem();
            for (int i = 0; i < save.channels.size(); i++) {
                if (save.channels.get(i).getName().equals(select)) {
                    ChannelManager channelManager = new ChannelManager(managernamefield.getText(),
                            managerpassfield.getText(), save.channels.get(i));
                    save.channelManagers.add(channelManager);
                    save.channels.get(i).setManager(channelManager);
                    break;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "you've forgot sth");
            alert.showAndWait();
        }
    }

    public void undo(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("..\\view\\CEOLogin.fxml");
    }
}