package lk.RoyalGatesHotels;

import animatefx.animation.AnimationFX;
import animatefx.animation.Bounce;
import animatefx.animation.BounceIn;
import animatefx.animation.SlideInUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sun.font.TrueTypeFont;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/RoyalGatesHotels/view/welcomePage.fxml"))));
        primaryStage.setTitle("Hotel Management System");
        primaryStage.getIcons().add(new Image("lk/RoyalGatesHotels/assets/icons/icon3.png"));
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }
}