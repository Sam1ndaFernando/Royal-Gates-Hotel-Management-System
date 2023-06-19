package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.model.EmployeeModel;
import lk.RoyalGatesHotels.model.RoomsModel;
import lk.RoyalGatesHotels.tableModel.EmployeeTM;
import lk.RoyalGatesHotels.tableModel.RoomTM;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminRoomReportController implements Initializable {
   // public AnchorPane guestReportContext;
    public Label lblTime;
    public Label lblDate;
    public AnchorPane RoomReportContext;
    public TableView tblRoomReport;
    public TableColumn colRoomNumber;
    public TableColumn colRoomType;
    public TableColumn colPrice;
    public TableColumn colStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(RoomReportContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        loadRoomsData();

    }

    private void loadRoomsData() {
        ObservableList<RoomTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = RoomsModel.getRoomData();
            while (result.next()){
                data.add(
                        new RoomTM(
                                result.getString("room_number"),
                                result.getString("room_type"),
                                result.getString("status"),
                                result.getString("price")
                        ));
            }
            tblRoomReport.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,RoomReportContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,RoomReportContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,RoomReportContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,RoomReportContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,RoomReportContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,RoomReportContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,RoomReportContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,RoomReportContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,RoomReportContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,RoomReportContext);
    }

    public void btnHallReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLREPORT,RoomReportContext);
    }

    public void btnEmployeeReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEEREPORT,RoomReportContext);
    }

    public void btnMealPackageReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,RoomReportContext);
    }


    public void btnPrint(ActionEvent actionEvent) {

        InputStream inputStream = this.getClass().getResourceAsStream("..//report//RoomReport.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
