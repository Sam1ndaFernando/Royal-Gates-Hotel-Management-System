package lk.RoyalGatesHotels.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ForgotPasswordFoamController implements Initializable {
    public AnchorPane frgotPassContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtEmployeeid;
    public JFXTextField txtUsername;
    public JFXTextField txtNewPassword;
    public JFXTextField txtConfirmPassword;

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,frgotPassContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,frgotPassContext);
    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,frgotPassContext);
    }

    public void btnUpdatePassword(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);
    }
}
