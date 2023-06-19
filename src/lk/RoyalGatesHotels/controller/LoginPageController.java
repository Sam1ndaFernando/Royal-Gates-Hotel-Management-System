package lk.RoyalGatesHotels.controller;

import animatefx.animation.*;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static lk.RoyalGatesHotels.util.Routes.ADMINDASHBOARD;

public class LoginPageController implements Initializable {
    public AnchorPane loginContext;
    public JFXTextField txtUsername;
    public Label lblForgotPassword;
    public Label lblTime;
    public Label lblDate;
    public JFXPasswordField txtPassword;

    static String login = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //new FadeInDown(loginContext).play();
        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);
    }

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if(username.equals("a")&&password.equals("b")){
            login="Admin";
            new ZoomIn(loginContext).play();
            Navigation.navigate(Routes.ADMINDASHBOARD,loginContext);

        }else {
            if(username.equals("c")&&password.equals("d")){
                login="Reception";
                new ZoomIn(loginContext).play();
                Navigation.navigate(Routes.RECEPTIONDASHBOARD,loginContext);
            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid username or password").show();
                txtUsername.requestFocus();
                txtPassword.requestFocus();
            }
        }
    }

    public void txtUsernameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }


}

