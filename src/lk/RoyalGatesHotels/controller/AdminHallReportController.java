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
import lk.RoyalGatesHotels.model.HallsModel;
import lk.RoyalGatesHotels.tableModel.HallTM;
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

public class AdminHallReportController implements Initializable {
    //public AnchorPane guestReportContext;
    public Label lblTime;
    public Label lblDate;

    public AnchorPane HallReportContext;
    public TableView tblHallReport;
    public TableColumn colHallNumber;
    public TableColumn colHallType;
    public TableColumn colPrice;
    public TableColumn colStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(HallReportContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        colHallType.setCellValueFactory(new PropertyValueFactory<>("hallType"));
        colHallNumber.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        loadHallData();
    }

    private void loadHallData() {
        ObservableList<HallTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = HallsModel.getHallData();
            while (result.next()){

                data.add(
                        new HallTM(
                                result.getString("hallNumber"),
                                result.getString("hall_type"),
                                result.getString("status"),
                                result.getString("price")
                        ));

            }
            tblHallReport.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,HallReportContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,HallReportContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,HallReportContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,HallReportContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,HallReportContext);
    }

    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,HallReportContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,HallReportContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,HallReportContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,HallReportContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,HallReportContext);
    }

    public void btnRoomReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOMREPORT,HallReportContext);
    }

    public void btnEmployeeReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEEREPORT,HallReportContext);
    }

    public void btnMealPackageReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,HallReportContext);
    }


    public void btnPrint(ActionEvent actionEvent) {

        InputStream inputStream = this.getClass().getResourceAsStream("..//report//HallReport.jrxml");
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
