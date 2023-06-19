package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class receptionistReservationDetailController implements Initializable {
    public AnchorPane reservationContext;
    public Label lblTime;
    public Label lblDate;
    public TableView roomTable;
    public TableView hallTable;
    public TableColumn colRoomNumber;
    public TableColumn colRoomReservationId;
    public TableColumn colRoomGuestId;
    public TableColumn colRoomCheckInDate;
    public TableColumn colRoomCheckOutDate;
    public TableColumn colHallReservationId;
    public TableColumn colHallNumber;
    public TableColumn colHallCheckInDate;
    public TableColumn colHallCheckOutDate;
    public TableColumn colHallGuestId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(reservationContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        // ROOM RESERVATION DETAIL
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colRoomReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colRoomGuestId.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colRoomCheckInDate.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colRoomCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));

        // HALL RESERVATION DETAIL
        colHallReservationId.setCellValueFactory(new PropertyValueFactory<>("email"));
        colHallNumber.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        colHallCheckInDate.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
        colHallCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
        colHallGuestId.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
        loadReservationDetailData();

    }

    private void loadReservationDetailData() {
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

    public void btnBack(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,reservationContext);
    }


}
