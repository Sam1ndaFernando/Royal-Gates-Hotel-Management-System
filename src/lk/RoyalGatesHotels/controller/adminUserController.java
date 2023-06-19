package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.model.EmployeeModel;
import lk.RoyalGatesHotels.model.UsersModel;
import lk.RoyalGatesHotels.to.Users;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class adminUserController implements Initializable {
    public AnchorPane ManagepsswrdContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtUsername;
    public JFXTextField txtName;
    public JFXTextField txtJobRole;
    public JFXTextField txtPassword;
    public JFXComboBox comBxEmployeeId;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(ManagepsswrdContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setEmployeeIds();
    }

    public void setEmployeeIds(){
        try {
            ObservableList<String> ids = UsersModel.loadEmployeeIds();
            comBxEmployeeId.setItems(ids);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,ManagepsswrdContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,ManagepsswrdContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,ManagepsswrdContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,ManagepsswrdContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,ManagepsswrdContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,ManagepsswrdContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,ManagepsswrdContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,ManagepsswrdContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,ManagepsswrdContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,ManagepsswrdContext);
    }


    public void btnAdd(ActionEvent actionEvent) {
        Users users = new Users(
                String.valueOf(comBxEmployeeId.getValue()),
                txtName.getText(),
                txtJobRole.getText(),
                txtUsername.getText(),
                txtPassword.getText()
        );

        try {
            boolean isAdd = UsersModel.addUsers(users);
            if(isAdd){
                new Alert(Alert.AlertType.CONFIRMATION,"Guest Added Successfully!").show();
                clearAll();
            }else{
                new Alert(Alert.AlertType.ERROR,"Guest Not Added!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnUpdate(ActionEvent actionEvent) {
        Users users = new Users(
                txtName.getText(),
                txtJobRole.getText(),
                txtUsername.getText(),
                txtPassword.getText(),
                String.valueOf(comBxEmployeeId.getValue())
        );

//        System.out.println(txtName.getText());
//        System.out.println(txtJobRole.getText());
//        System.out.println(txtUsername.getText());
//        System.out.println(txtPassword.getText());
//        System.out.println(String.valueOf(comBxEmployeeId.getValue()));

        try {
            boolean isUpdate = UsersModel.updateUsers(users);
            if(isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"User Updated Successfully!").show();
                clearAll();
            }else{
                new Alert(Alert.AlertType.ERROR,"User Not Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearAll() {
                txtName.clear();
                txtJobRole.clear();
                txtUsername.clear();
                txtPassword.clear();
    }

    public void btnCancel(ActionEvent actionEvent) {
        txtName.setText(null);
        txtJobRole.setText(null);
        txtUsername.setText(null);
        txtPassword.setText(null);
        comBxEmployeeId.setValue(null);

    }



    public void EmployeeIdOnAction(ActionEvent actionEvent) {

        try {
            ResultSet result = UsersModel.searchEmployee(String.valueOf(comBxEmployeeId.getValue()));
            if(result.next()){
                txtName.setText(result.getString("name"));
                txtJobRole.setText(result.getString("job_role"));
                txtUsername.setText(result.getString("username"));
                txtPassword.setText(result.getString("password"));
            }else{
                ResultSet resultSet = EmployeeModel.searchEmployee(String.valueOf(comBxEmployeeId.getValue()));
                if(resultSet.next()){
                    txtName.setText(resultSet.getString("name"));
                    txtJobRole.setText(resultSet.getString("jobRole"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
