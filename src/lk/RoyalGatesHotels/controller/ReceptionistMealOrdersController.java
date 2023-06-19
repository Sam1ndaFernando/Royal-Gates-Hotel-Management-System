package lk.RoyalGatesHotels.controller;

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
import lk.RoyalGatesHotels.model.EmployeeModel;
import lk.RoyalGatesHotels.model.GuestModel;
import lk.RoyalGatesHotels.model.MealOdersModel;
import lk.RoyalGatesHotels.model.MealPackgesModel;
import lk.RoyalGatesHotels.to.MealOders;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReceptionistMealOrdersController implements Initializable {
    public AnchorPane receptionMealOdersContext;
    public Label lblTime;
    public Label lblDate;
    public JFXComboBox comBxGuestId;
    public JFXComboBox comBxPackageId;
    public JFXTextField txtOrderId;
    public JFXDatePicker DatepickerDate;
    public JFXTextField txtQty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(receptionMealOdersContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setMealOrdersId();
        setGuestIds();
        setPackageIds();
    }

    private void setPackageIds() {
        try {
            ObservableList<String> options = MealPackgesModel.loadPackageIds();
            comBxPackageId.setItems(options);
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

    private void setMealOrdersId() {
        try {
            String lastMealOrdersId = MealOdersModel.getLastMealOrdersId();
            if (lastMealOrdersId == null) {
                txtOrderId.setText("MO0001");
            } else {
                String[] split = lastMealOrdersId.split("[M][O]");
                int lastDigits = Integer.parseInt(split[1]);
                lastDigits++;
                String newMealOrdersId = String.format("MO%04d", lastDigits);
                txtOrderId.setText(newMealOrdersId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e + "").show();
        }

    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD, receptionMealOdersContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN, receptionMealOdersContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE, receptionMealOdersContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS, receptionMealOdersContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION, receptionMealOdersContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT, receptionMealOdersContext);
    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST, receptionMealOdersContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE, receptionMealOdersContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS, receptionMealOdersContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT, receptionMealOdersContext);
    }

    public void btnOrderNow(ActionEvent actionEvent) {
        MealOders mealOders = new MealOders(
                txtOrderId.getText(),
                String.valueOf(comBxGuestId.getValue()),
                String.valueOf(DatepickerDate.getValue()),
                Integer.parseInt(txtQty.getText()),
                String.valueOf(comBxPackageId.getValue())
        );

        try {
            boolean isAdd = MealOdersModel.addOders(mealOders);
            if (isAdd) {
                new Alert(Alert.AlertType.CONFIRMATION, "Meal Oder Added Successfully!").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Meal Oder Not Added!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnUpdateOrder(ActionEvent actionEvent) {
        String guestId = null;
        if (comBxGuestId.getValue() != null) {
            guestId = String.valueOf(comBxGuestId.getValue());
        } else {
            guestId = comBxGuestId.getPromptText();
        }

        String pkgId = null;
        if (comBxPackageId.getValue() != null) {
            pkgId = String.valueOf(comBxPackageId.getValue());
        } else {
            pkgId = comBxPackageId.getPromptText();
        }

        MealOders mealOders = new MealOders(
                txtOrderId.getText(),
                guestId,
                String.valueOf(DatepickerDate.getValue()),
                Integer.parseInt(txtQty.getText()),
                pkgId
        );

        try {
            boolean isUpdate = MealOdersModel.updateOrder(mealOders);
            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Meal Oder Updated Successfully!").show();
                Navigation.navigate(Routes.RECEPTIONMEALODERS,receptionMealOdersContext);
            }else{
                new Alert(Alert.AlertType.ERROR, "Not Updated!").show();
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }


    }


    public void btnCancelOrder(ActionEvent actionEvent) {
        try {
            Navigation.navigate(Routes.RECEPTIONMEALODERS,receptionMealOdersContext);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void comBxGuestIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result = MealOdersModel.searchOrder(txtOrderId.getText());
            if (result.next()) {
                comBxGuestId.setPromptText(result.getString("customer_id"));
                DatepickerDate.setValue(LocalDate.parse(result.getString("date")));
                txtQty.setText(result.getString("qty"));
                comBxPackageId.setPromptText(result.getString("pkg_id"));

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
