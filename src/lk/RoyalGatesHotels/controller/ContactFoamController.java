package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
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

public class ContactFoamController implements Initializable {
    public AnchorPane contactContext;
    public Label lblTime;
    public Label lblDate;

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,contactContext);
    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        if(LoginPageController.login.equals("Admin")){
            Navigation.navigate(Routes.ADMINDASHBOARD,contactContext);
        }else{
            Navigation.navigate(Routes.RECEPTIONDASHBOARD,contactContext);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(contactContext).play();
        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);
    }
}
