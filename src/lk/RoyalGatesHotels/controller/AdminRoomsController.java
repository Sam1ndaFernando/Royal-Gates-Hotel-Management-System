package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.model.RoomsModel;
import lk.RoyalGatesHotels.to.Room;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.RegExPattern;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminRoomsController implements Initializable {
    public AnchorPane adminRoomsContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtRoomNumber;
    public JFXTextField txtRoomType;
    public JFXTextField txtPrice;

    public JFXComboBox cmbAvailability;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(adminRoomsContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setAdminRoom();

        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Available");
        options.add("Unavailable");
        cmbAvailability.setItems(options);

    }


    private void setAdminRoom() {
        try {
            String lastAdminRoomId= RoomsModel.getLastAdminRoomId();
            if(lastAdminRoomId==null){
                txtRoomNumber.setText("R0001");
            }else{
                String[] split=lastAdminRoomId.split("[R]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newAdminRoomId=String.format("R%04d", lastDigits);
                txtRoomNumber.setText(newAdminRoomId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }
    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN, adminRoomsContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM, adminRoomsContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN, adminRoomsContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,adminRoomsContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE, adminRoomsContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE, adminRoomsContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS, adminRoomsContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT, adminRoomsContext);
    }

    public void btnAddRoom(ActionEvent actionEvent) {
        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){
            Room room = new Room(
                    Integer.parseInt(txtRoomNumber.getText()),
                    txtRoomType.getText(),
                    "Available",
                    Double.parseDouble(txtPrice.getText())
            );

            try {
                boolean isAdd = RoomsModel.addRoom(room);
                if(isAdd){
                    new Alert(Alert.AlertType.CONFIRMATION,"Room Added Successfully!").show();
                    clearAll();
                    setAdminRoom();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Room Not Added!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            txtPrice.requestFocus();
        }

    }

    public void btnUpdateRoom(ActionEvent actionEvent) {
        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){
            Room room = new Room(
                    Integer.parseInt(txtRoomNumber.getText()),
                    txtRoomType.getText(),
                    String.valueOf(cmbAvailability.getValue()),
                    Double.parseDouble(txtPrice.getText())
            );

            try {
                boolean isUpdate = RoomsModel.updateRoom(room);
                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Room Updated Successfully!").show();
                    clearAll();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Room Not Updated!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            txtPrice.requestFocus();
        }

    }

    public void btnCancel(ActionEvent actionEvent) {
        clearAll();
    }

    public void clearAll(){
        txtRoomType.setText(null);
        cmbAvailability.setPromptText(null);
        txtPrice.setText(null);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT, adminRoomsContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS, adminRoomsContext);
    }


    public void RoomNumberOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result = RoomsModel.searchRoom(txtRoomNumber.getText());
            if(result.next()){
                txtRoomType.setText(result.getString("room_type"));
                txtPrice.setText(result.getString("price"));
                cmbAvailability.setPromptText(result.getString("status"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AvailabilityOnAction(ActionEvent actionEvent) {

    }
}
