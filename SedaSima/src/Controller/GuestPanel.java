package Controller;

import Model.PageLoader;
import Model.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class GuestPanel {
    @FXML
    TextArea textarea;
    @FXML
    TextField ratefield, searchfield;
    @FXML
    TableView<Program> tableView = new TableView<>();
    @FXML
    TableColumn<Program, String> name = new TableColumn<>();
    @FXML
    TableColumn<Program, String> time = new TableColumn<>();
    @FXML
    ListView<String> listfield, textfield;
    @FXML
    TableColumn<Program, String> catagory;

    @FXML
    TableColumn<Program, String> duration;

    @FXML
    TableColumn<Program, String> date;
    @FXML
    TableColumn<Program, String> reptimefield;

    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        catagory.setCellValueFactory(new PropertyValueFactory<>("category"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        reptimefield.setCellValueFactory(new PropertyValueFactory<>("repStart"));
        time.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        for (int i = 0; i < save.channels.size(); i++) {
            listfield.getItems().add(save.channels.get(i).getName());
        }
    }

    public void showInfo(MouseEvent mouseEvent) {
        for (int i = 0; i < save.channels.size(); i++) {
            String select = listfield.getSelectionModel().getSelectedItem();
            if ((save.channels.get(i).getName().equals(select))) {
                tableView.getItems().setAll((Program) null);
                for (int j = 0; j < save.channelManagers.size(); j++) {
                    if (save.channels.get(i).getName().equals(save.channelManagers.get(j).getChannel().getName())) {
                        ObservableList<Program> data = FXCollections.observableArrayList
                                (save.channelManagers.get(j)
                                        .getChannel().getPrograms());
                        System.out.println(save.channelManagers.get(j)
                                .getChannel().getPrograms().get(0).getCategory());
                        tableView.setItems(data);
                    }
                }
            }
        }
    }

    static Program tmp;

    public void back(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("..\\view\\CEOLogin.fxml");
    }

    public void rate(ActionEvent actionEvent) {
        try {
            for (int i = 0; i < save.channelManagers.size(); i++) {
                for (int j = 0; j < save.channelManagers.get(i).getChannel().getPrograms().size(); j++) {
                    if (tmp.getName().equals(save.channelManagers.get(i).getChannel().getPrograms().get(j).getName())) {
                        save.channelManagers.get(i).getChannel().getPrograms().get(j).
                                setRate(Integer.parseInt(ratefield.getText()) + save.channelManagers.get(i).getChannel()
                                        .getPrograms().get(j).getRate());
                        ratefield.setText(null);
                        textarea.setText(String.valueOf(save.channelManagers.get(i).getChannel()
                                .getPrograms().get(j).getRate()));
                    }
                }
            }
        } catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "please input valid amount");
            alert.showAndWait();
        }
    }

    public void init(MouseEvent mouseEvent) {
        Program p = tableView.getSelectionModel().getSelectedItem();
        tmp = p;
        textarea.setText(String.valueOf(p.getRate()));
    }

    public void search(ActionEvent actionEvent) {
        for (int i = 0; i < save.channelManagers.size(); i++) {
            for (int j = 0; j < save.channelManagers.get(i).getChannel().getPrograms().size(); j++) {
                if (searchfield.getText().equalsIgnoreCase(save.channelManagers.get(i).getChannel().
                        getPrograms().get(j).getName())) {
                    tableView.getItems().setAll((Program) null);
                    tableView.getItems().add(save.channelManagers.get(i)
                            .getChannel().getPrograms().get(j));
                }
//                else {
//                    Alert alert=new Alert(Alert.AlertType.ERROR,"not found");
//                    alert.showAndWait();
//                }
            }
        }
    }

}
