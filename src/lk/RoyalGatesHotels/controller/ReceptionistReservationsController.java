package lk.RoyalGatesHotels.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.model.*;
import lk.RoyalGatesHotels.to.HallReservation;
import lk.RoyalGatesHotels.to.RoomReservation;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReceptionistReservationsController implements Initializable {
    public AnchorPane reservationContext;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox comBxGuestId;
    public JFXComboBox comBxRoomNumber;
    public JFXComboBox comBxHallNumber;
    public JFXDatePicker DatepickerCheckInDate;
    public JFXDatePicker DatepickerCheckOutDate;
    public JFXTextField txtHallReservationId;
    public JFXTextField txtRoomReservationId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(reservationContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setRoomReserveNo();
        setHallReserveNo();

        setGuestIds();
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

    private void setGuestIds() {
        try {
            ObservableList<String> options = GuestModel.loadGuestIds();
            comBxGuestId.setItems(options);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRoomReserveNo() {
        try {
            String lastRoomReservationId= RoomReservationModel.getLastRoomReservationId();
            if(lastRoomReservationId==null){
                txtRoomReservationId.setText("RR0001");
            }else{
                String[] split=lastRoomReservationId.split("[R][R]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newRoomReservationId=String.format("RR%04d", lastDigits);
                txtRoomReservationId.setText(newRoomReservationId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    private void setHallReserveNo() {
        try {
            String lastHallReservationId= HallReservationModel.getLastHallReservationId();
            if(lastHallReservationId==null){
                txtHallReservationId.setText("HR0001");
            }else{
                String[] split=lastHallReservationId.split("[H][R]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newHallReservationId=String.format("HR%04d", lastDigits);
                txtHallReservationId.setText(newHallReservationId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }


    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD,reservationContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,reservationContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,reservationContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS,reservationContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,reservationContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT,reservationContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,reservationContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,reservationContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,reservationContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,reservationContext);
    }

    public void btnRoomReserve(ActionEvent actionEvent) {

        RoomReservation roomReservation = new RoomReservation(
                String.valueOf(comBxRoomNumber.getValue()),
                String.valueOf(comBxGuestId.getValue()),
                txtRoomReservationId.getText(),
                String.valueOf(DatepickerCheckOutDate.getValue()),
                String.valueOf(DatepickerCheckInDate.getValue())
        );

        try {
            boolean isAdd = RoomReservationModel.addReservation(roomReservation);
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Room Reserved Successfully!").show();
                clearAll();
                setRoomReserveNo();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearAll() {
        comBxRoomNumber.setValue(null);
        comBxHallNumber.setValue(null);
        comBxGuestId.setValue(null);
        DatepickerCheckOutDate.setValue(null);
        DatepickerCheckInDate.setValue(null);
    }

    public void btnHallReserve(ActionEvent actionEvent) {

        HallReservation hallReservation = new HallReservation(
                String.valueOf(comBxHallNumber.getValue()),
                String.valueOf(comBxGuestId.getValue()),
                txtHallReservationId.getText(),
                String.valueOf(DatepickerCheckOutDate.getValue()),
                String.valueOf(DatepickerCheckInDate.getValue())
        );

        try {
            boolean isAdd = HallReservationModel.addReservation(hallReservation);
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Hall Reserved Successfully!").show();
                clearAll();
                setHallReserveNo();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnCancelReserve(ActionEvent actionEvent) {
        clearAll();
    }


    public void btnReservationDetails(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECERVATIONDETAILS,reservationContext);
    }
}
