package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.model.ComplainModel;
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.model.RoomsModel;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReceptionistDashboardController implements Initializable {
    public AnchorPane receptionContext;
    public Label lblTime;
    public Label lblDate;
    public Label lblTotalRooms;
    public Label lblBookedRooms;
    public Label lblBookedHalls;
    public Label lblAvailableHalls;
    public Label lblAvailableRooms;
    public Label lblComplaints;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(receptionContext).play();
        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

       setDashboardLabels();
    }

    public void setDashboardLabels(){

        try {
            int roomCount = RoomsModel.getRoomCount();
            lblTotalRooms.setText(String.valueOf(roomCount));

            int complaint = ComplainModel.getComplaintCount();
            lblComplaints.setText(String.valueOf(complaint));

            int bookedRooms = RoomsModel.getBookedRoomsCount();
            lblBookedRooms.setText(String.valueOf(bookedRooms));

            int availableRooms = RoomsModel.getAvailableRoomsCount();
            lblAvailableRooms.setText(String.valueOf(availableRooms));

            int bookedHalls = HallsModel.getBookedHallsCount();
            lblBookedHalls.setText(String.valueOf(bookedHalls));

            int availableHallsCount = HallsModel.getAvailableHallsCount();
            lblAvailableHalls.setText(String.valueOf(availableHallsCount));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD,receptionContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,receptionContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,receptionContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS,receptionContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,receptionContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,receptionContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,receptionContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        System.out.println("gggg");
        Navigation.navigate(Routes.CONTACT,receptionContext);
    }

    public void btnTotalRooms(ActionEvent actionEvent) {
    }

    public void btnBookedRooms(ActionEvent actionEvent) {
    }

    public void btnBookedHalls(ActionEvent actionEvent) {
    }

    public void btnAvailableHalls(ActionEvent actionEvent) {
    }

    public void btnAvailableRooms(ActionEvent actionEvent) {
    }

    public void btnComplaints(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,receptionContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,receptionContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT,receptionContext);
    }


}
