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
import lk.RoyalGatesHotels.model.EmployeeModel;
import lk.RoyalGatesHotels.model.GuestModel;
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.model.MealPackgesModel;
import lk.RoyalGatesHotels.to.Employee;
import lk.RoyalGatesHotels.to.Guest;
import lk.RoyalGatesHotels.to.Hall;
import lk.RoyalGatesHotels.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminBanquetHallsController implements Initializable {
    public AnchorPane adminHallContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtHallNumber;
    public JFXTextField txtHallType;
    public JFXTextField txtPrice;

    public JFXComboBox cmbAvailability;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(adminHallContext).play();
        setAdminHall();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);


        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Available");
        options.add("Unavailable");
        cmbAvailability.setItems(options);
    }

    private void setAdminHall() {
        try {
            String lastAdminHallId= HallsModel.getLastAdminHallId();
            if(lastAdminHallId==null){
                txtHallNumber.setText("H0001");
            }else{
                String[] split=lastAdminHallId.split("[H]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newAdminHallId=String.format("H%04d", lastDigits);
                txtHallNumber.setText(newAdminHallId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,adminHallContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,adminHallContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,adminHallContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,adminHallContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,adminHallContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,adminHallContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,adminHallContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,adminHallContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,adminHallContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,adminHallContext);
    }

    public void btnAddHall(ActionEvent actionEvent) throws IOException {
        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){
            Hall hall = new Hall(
                    txtHallType.getText(),
                    txtHallNumber.getText(),
                    String.valueOf(cmbAvailability.getValue()),
                    Double.parseDouble(txtPrice.getText())
            );

            try {
                boolean isAdd = HallsModel.addHall(hall);
                if(isAdd){
                    new Alert(Alert.AlertType.CONFIRMATION,"Hall Added Successfully!").show();
                    Navigation.navigate(Routes.ADMINHALLS,adminHallContext);
                }else{
                    new Alert(Alert.AlertType.ERROR,"Hall Not Added!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            txtPrice.requestFocus();
        }

    }


    public void btnUpdateHall(ActionEvent actionEvent) throws IOException {
        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){
            String availability=null;
            if(cmbAvailability.getValue()==null){
                availability=cmbAvailability.getPromptText();
            }else{
                availability=String.valueOf(cmbAvailability.getValue());
            }

            Hall hall = new Hall(
                    txtHallType.getText(),
                    txtHallNumber.getText(),
                    availability,
                    Double.parseDouble(txtPrice.getText())
            );

            try {
                boolean isUpdate = HallsModel.updateHall(hall);
                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Hall Updated Successfully!").show();
                    Navigation.navigate(Routes.ADMINHALLS,adminHallContext);
                }else{
                    new Alert(Alert.AlertType.ERROR,"Hall Not Updated!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            txtPrice.requestFocus();
        }


    }
    public void btnCancelHall(ActionEvent actionEvent){
        try {
            Navigation.navigate(Routes.ADMINHALLS,adminHallContext);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtHallNumberOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result = HallsModel.searchHall(txtHallNumber.getText());
            if(result.next()){
                txtHallType.setText(result.getString("hall_type"));
                cmbAvailability.setPromptText(result.getString("status"));
                txtPrice.setText(result.getString("price"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AvailabilityOnAction(ActionEvent actionEvent) {
    }
}
