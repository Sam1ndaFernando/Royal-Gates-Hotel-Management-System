package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.model.*;
import lk.RoyalGatesHotels.to.Complain;
import lk.RoyalGatesHotels.to.Employee;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ReceptionistComplainController implements Initializable {
    public AnchorPane receptionComplainContext;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox comBxHallNumber;
    public JFXComboBox comBxRoomNumber;
    public JFXTextField txtComplainId;
    public JFXDatePicker DatepickerDate;
    public JFXTimePicker timePikrTime;
    public JFXTextArea txtAreaDescription;
    public JFXTextField txtGuestId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(receptionComplainContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setComplainId();
        setRoomNumbers();
        setHallNumbers();
    }

    private void setHallNumbers() {
        try {
            ObservableList<String> option = HallsModel.loadHallNumbers();
            comBxHallNumber.setItems(option);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRoomNumbers() {
        try {
            ObservableList<String> options = RoomsModel.loadRoomNumbers();
            comBxRoomNumber.setItems(options);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setComplainId() {
        try {
            String lastComplainId= ComplainModel.getLastComplainId();
            if(lastComplainId==null){
                txtComplainId.setText("C0001");
            }else{
                String[] split=lastComplainId.split("[C]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newComplainId=String.format("C%04d", lastDigits);
                txtComplainId.setText(newComplainId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD,receptionComplainContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,receptionComplainContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,receptionComplainContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS,receptionComplainContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,receptionComplainContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,receptionComplainContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,receptionComplainContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT,receptionComplainContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,receptionComplainContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,receptionComplainContext);
    }


    public void btnAdd(ActionEvent actionEvent) {
        Complain complain = new Complain(
                String.valueOf(comBxRoomNumber.getValue()),
                String.valueOf(comBxHallNumber.getValue()),
                txtComplainId.getText(),
                txtGuestId.getText(),
                String.valueOf(DatepickerDate.getValue()),
                String.valueOf(timePikrTime.getValue()),
                txtAreaDescription.getText()
        );

        try {
            boolean isAdd = ComplainModel.addComplain(complain);
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Complain Added Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONCOMPLAIN,receptionComplainContext);
            }else{
                new Alert(Alert.AlertType.ERROR,"Complain Not Added!").show();
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnUpdate(ActionEvent actionEvent) {
        Complain complain = new Complain(
                String.valueOf(comBxRoomNumber.getValue()),
                String.valueOf(comBxHallNumber.getValue()),
                txtComplainId.getText(),
                txtGuestId.getText(),
                String.valueOf(DatepickerDate.getValue()),
                String.valueOf(timePikrTime.getValue()),
                txtAreaDescription.getText()
        );

        try {
            boolean isUpdate = ComplainModel.updateComplain(complain);
            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Complaint Updated Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONCOMPLAIN,receptionComplainContext);
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }


    }



    public void btnCancel(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,receptionComplainContext);
    }

    public void txtComplainIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result = ComplainModel.searchComplain(txtComplainId.getText());
            if(result.next()){
                comBxRoomNumber.setPromptText(result.getString("room_number"));
                comBxHallNumber.setPromptText(result.getString("hall_number"));
                txtGuestId.setText(result.getString("customer_id"));
                txtAreaDescription.setText(result.getString("description"));
                timePikrTime.setValue(LocalTime.parse(result.getString("time")));
                DatepickerDate.setValue(LocalDate.parse(result.getString("date")));

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
