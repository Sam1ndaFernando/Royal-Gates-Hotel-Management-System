package lk.RoyalGatesHotels.util;
import animatefx.animation.FadeIn;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {

            case WELCOMEPAGE:
                window.setTitle("Dashboard");
                window.setResizable(false);
                initUI("welcomePage.fxml");
                break;

            case LOGINPAGE:
                window.setTitle("Login Page");
                window.setResizable(false);
                new FadeIn(pane).play();
                initUI("LoginPage.fxml");
                break;

            case RECEPTIONDASHBOARD:
                window.setTitle("RECEPTION DASHBOARD");
                window.setResizable(false);
                initUI("receptionistDashboard.fxml");
                break;

            case ADMINDASHBOARD:
                window.setTitle("ADMIN DASHBOARD");
                window.setResizable(false);
                initUI("adminDashboard.fxml");
                break;

            case ADMINEMPLOYEE:
                window.setTitle("ADMIN Employee");
                window.setResizable(false);
                initUI("adminEmployee.fxml");
                break;

            case ADMINROOM:
                window.setTitle("ADMIN ROOM");
                window.setResizable(false);
                initUI("adminRooms.fxml");
                break;

            case ADMINMEALPLAN:
                window.setTitle("ADMIN MEAL PLAN");
                window.setResizable(false);
                initUI("adminMealPlans.fxml");
                break;

            case ADMINHALLS:
                window.setTitle("ADMIN HALLS");
                window.setResizable(false);
                initUI("adminBanquetHalls.fxml");
                break;

            case ADMINUSER:
                window.setTitle("ADMIN USER");
                window.setResizable(false);
                initUI("adminUser.fxml");
                break;

            case ADMINMEALPACKAGEREPORT:
                window.setTitle("MEAL PACKAGE REPORT");
                window.setResizable(false);
                initUI("adminMealPackagesReport.fxml");
                break;

            case ADMINHALLREPORT:
                window.setTitle("HALL REPORT");
                window.setResizable(false);
                initUI("adminHallReport.fxml");
                break;

            case EMPLOYEEREPORT:
                window.setTitle("EMPLOYEE REPORT");
                window.setResizable(false);
                initUI("adminEmployeeReport.fxml");
                break;

            case ADMINROOMREPORT:
                window.setTitle("ROOM REPORT");
                window.setResizable(false);
                initUI("adminRoomReport.fxml");
                break;

            case FORGOTPASSWORD:
                window.setTitle("FORGOT PASSWORD");
                window.setResizable(false);
                initUI("ForgotPasswordFoam.fxml");
                break;

            case CONTACT:
                window.setTitle("CONTACT");
                window.setResizable(false);
                initUI("contactFoam.fxml");
                break;

            case ABOUTUS:
                window.setTitle("ABOUTUS");
                window.setResizable(false);
                initUI("aboutUsFoam.fxml");
                break;

            case RECEPTIONRESERVATION:
                window.setTitle("RECEPTION RESERVATION");
                window.setResizable(false);
                initUI("receptionistReservations.fxml");
                break;

            case RECEPTIONMEALODERS:
                window.setTitle("RECEPTION MEAL ODERS");
                window.setResizable(false);
                initUI("receptionistMealOrders.fxml");
                break;

            case RECEPTIONMARKMAINTANCE:
                window.setTitle("RECEPTION MARKMAINTANCE");
                window.setResizable(false);
                initUI("receptionistMarkMaintenance.fxml");
                break;

            case RECEPTIONCOMPLAIN:
                window.setTitle("RECEPTION COMPLAIN");
                window.setResizable(false);
                initUI("receptionistComplain.fxml");
                break;

            case RECEPTIONGUEST:
                window.setTitle("RECEPTION GUEST");
                window.setResizable(false);
                initUI("receptionistGuest.fxml");
                break;

            case RECEPTIONPAYMENT:
                window.setTitle("RECEPTION GUEST");
                window.setResizable(false);
                initUI("receptionistPayments.fxml");
                break;

            case RECERVATIONDETAILS:
                window.setTitle("RECERVATION DETAILS");
                window.setResizable(false);
                initUI("receptionistReservationDetail.fxml");
                break;

            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/RoyalGatesHotels/view/"+location)));
    }
}
