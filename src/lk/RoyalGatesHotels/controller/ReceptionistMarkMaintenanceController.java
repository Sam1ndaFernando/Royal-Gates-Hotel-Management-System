package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.model.MaintenanceModel;
import lk.RoyalGatesHotels.model.MealOdersModel;
import lk.RoyalGatesHotels.model.RoomsModel;
import lk.RoyalGatesHotels.to.Maintenance;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.model.MaintenanceModel;
import lk.RoyalGatesHotels.model.RoomsModel;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReceptionistMarkMaintenanceController implements Initializable {
    public AnchorPane markMaintanceContext;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox comBxRoomNumber;
    public JFXTextField txtMaintenanceId;
    public JFXDatePicker datepickerDate;
    public JFXComboBox comBxHallNumber;
    public JFXTimePicker timePikrStartTime;
    public JFXTimePicker timePikrEndTime;

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        new Pulse(markMaintanceContext).play();
//
//        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
//        DateTime.localTime(lblTime);
//
//        setRoomNumbers();
//        setHallNumbers();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(markMaintanceContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setRoomNumbers();
        setHallNumbers();
        setRoomMaintenanceId();
        setHallMaintenanceId();
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

    private void setRoomMaintenanceId() {
        try {
            String lastMaintenanceId = MaintenanceModel.getLastRoomMaintenanceId();
            if (lastMaintenanceId == null) {
                txtMaintenanceId.setText("RM0001");
            } else {
                String[] split = lastMaintenanceId.split("[R][M]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newMaintenanceId = String.format("RM%04d", lastDigits);
                txtMaintenanceId.setText(newMaintenanceId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void setHallMaintenanceId() {
        try {
            String lastMaintenanceId = MaintenanceModel.getLastHallMaintenanceId();
            if (lastMaintenanceId == null) {
                txtMaintenanceId.setText("HM0001");
            } else {
                String[] split = lastMaintenanceId.split("[H][M]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newMaintenanceId = String.format("HM%04d", lastDigits);
                txtMaintenanceId.setText(newMaintenanceId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD,markMaintanceContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,markMaintanceContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS,markMaintanceContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,markMaintanceContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT,markMaintanceContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,markMaintanceContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,markMaintanceContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,markMaintanceContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,markMaintanceContext);
    }

    public void btnMark(ActionEvent actionEvent) {

        String number=null;
        if(comBxHallNumber.getValue()!=null){
            number=String.valueOf(comBxHallNumber.getValue());
        }else{
            number=String.valueOf(comBxRoomNumber.getValue());
        }

        Maintenance maintenance = new Maintenance(
                txtMaintenanceId.getText(),
                number,
                String.valueOf(datepickerDate.getValue()),
                String.valueOf(timePikrStartTime.getValue()),
                String.valueOf(timePikrEndTime.getValue())
        );

        try {
        if(comBxRoomNumber.getValue()!=null){
            boolean isAdd = MaintenanceModel.addRoomMaintenance(maintenance);
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Added Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Added!").show();
            }

        }else if(comBxHallNumber.getValue()!=null){
            boolean isAdd = MaintenanceModel.addHallMaintenance(maintenance);
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Added Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
            }else{
                new Alert(Alert.AlertType.ERROR,"Not Added!").show();
            }
        }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnUpdate(ActionEvent actionEvent) {
        String number=null;
        if(comBxHallNumber.getValue()!=null){
            number=String.valueOf(comBxHallNumber.getValue());
        }else{
            number=String.valueOf(comBxRoomNumber.getValue());
        }

        Maintenance maintenance = new Maintenance(
                txtMaintenanceId.getText(),
                number,
                String.valueOf(datepickerDate.getValue()),
                String.valueOf(timePikrStartTime.getValue()),
                String.valueOf(timePikrEndTime.getValue())
        );

        try {
            if(comBxRoomNumber.getValue()!=null){
                boolean isUpdate = MaintenanceModel.updateRoomMaintenance(maintenance);
                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!").show();
                    Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
                }else{
                    new Alert(Alert.AlertType.ERROR,"Not Updated!").show();
                }

            }else if(comBxHallNumber.getValue()!=null){
                boolean isUpdate = MaintenanceModel.updateHallMaintenance(maintenance);
                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Not Updated!").show();
                }
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void btnCancel(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,markMaintanceContext);
    }

    public void comBxRoomNumberOnAction(ActionEvent actionEvent) {
        setRoomMaintenanceId();
    }

    public void comBxHallNumberOnAction(ActionEvent actionEvent) {
        setHallMaintenanceId();
    }
}
