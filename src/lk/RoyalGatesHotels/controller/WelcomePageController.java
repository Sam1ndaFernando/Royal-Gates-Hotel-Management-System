package lk.RoyalGatesHotels.controller;

import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomePageController implements Initializable {
    public AnchorPane welcmContext;

    public void btnWelcomePageOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE, welcmContext);
        new FadeIn(welcmContext).play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeIn(welcmContext).play();
    }
}
