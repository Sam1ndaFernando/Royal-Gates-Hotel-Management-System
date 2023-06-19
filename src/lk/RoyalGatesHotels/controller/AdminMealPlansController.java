package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.model.EmployeeModel;
import lk.RoyalGatesHotels.model.GuestModel;
import lk.RoyalGatesHotels.model.MealPackgesModel;
import lk.RoyalGatesHotels.to.Employee;
import lk.RoyalGatesHotels.to.Guest;
import lk.RoyalGatesHotels.to.MealPackges;
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

public class AdminMealPlansController implements Initializable {
    public JFXTextArea txtAreaDescription;
    public JFXTextField txtPrice;
    public AnchorPane adminMealplansContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtPackageId;
    public JFXTextField txtMealPlans;
    public JFXTextField txtMealType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(adminMealplansContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setMealPackage();
    }

    private void setMealPackage() {
        try {
            String lastMealPkgId= MealPackgesModel.getLastMealPkgId();
            if(lastMealPkgId==null){
                txtPackageId.setText("M0001");
            }else{
                String[] split=lastMealPkgId.split("[M]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newMealPkgId=String.format("M%04d", lastDigits);
                txtPackageId.setText(newMealPkgId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public void btnAddMeal(ActionEvent actionEvent) {
        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){
            MealPackges mealPackges = new MealPackges(
                    txtPackageId.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    txtAreaDescription.getText(),
                    txtMealPlans.getText(),
                    txtMealType.getText()
            );

            try {
                boolean isAdd = MealPackgesModel.addPackage(mealPackges);
                if(isAdd){
                    new Alert(Alert.AlertType.CONFIRMATION,"Meal Package Added Successfully!").show();
                    clearAll();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Meal Package Not Added!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            txtPrice.requestFocus();
        }
    }


    public void btnUpdateMeal(ActionEvent actionEvent) {
        boolean isPriceMatched = RegExPattern.getPricePattern().matcher(txtPrice.getText()).matches();

        if(isPriceMatched){
            MealPackges mealPackges = new MealPackges(
                    txtPackageId.getText(),
                    Double.parseDouble(txtPrice.getText()),
                    txtAreaDescription.getText(),
                    txtMealPlans.getText(),
                    txtMealType.getText()
            );

            try {
                boolean isUpdate = MealPackgesModel.updatePackage(mealPackges);
                if(isUpdate){
                    new Alert(Alert.AlertType.CONFIRMATION,"Meal Updated Successfully!").show();
                    clearAll();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Meal Not Updated!").show();
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
        txtPrice.setText(null);
        txtAreaDescription.setText(null);
        txtMealPlans.setText(null);
        txtMealType.setText(null);
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,adminMealplansContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,adminMealplansContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,adminMealplansContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,adminMealplansContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,adminMealplansContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,adminMealplansContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,adminMealplansContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,adminMealplansContext);
    }


    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,adminMealplansContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,adminMealplansContext);
    }


    public void PackageIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result = MealPackgesModel.searchMealPlan(txtPackageId.getText());
            if (result.next()) {
                txtPrice.setText(result.getString("price"));
                txtAreaDescription.setText(result.getString("description"));
                txtMealPlans.setText(result.getString("meal_plan"));
                txtMealType.setText(result.getString("type"));
            } else {
                new Alert(Alert.AlertType.ERROR, "Meal Package does not exist!").show();
                clearAll();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
