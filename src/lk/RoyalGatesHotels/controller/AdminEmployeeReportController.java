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
import lk.RoyalGatesHotels.tableModel.EmployeeTM;
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

public class AdminEmployeeReportController implements Initializable {
    public AnchorPane employeeReportContext;
    public Label lblTime;
    public Label lblDate;


    public TableView tblEmployee;
    public TableColumn colEmployeeId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colAddress;
    public TableColumn colMobile;
    public TableColumn colEmail;
    public TableColumn colJoinDate;
    public TableColumn colJobRole;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Pulse(employeeReportContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colJoinDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        colJobRole.setCellValueFactory(new PropertyValueFactory<>("jobRole"));

        loadEmployeeData();



    }

    private void loadEmployeeData() {
        ObservableList<EmployeeTM> data = FXCollections.observableArrayList();
        try {
            ResultSet result = EmployeeModel.getEmployeeData();
            while (result.next()){

                data.add(
                        new EmployeeTM(
                                result.getString("employeeId"),
                                result.getString("name"),
                                result.getString("address"),
                                result.getString("join_date"),
                                result.getString("nic"),
                                result.getString("Email"),
                                result.getString("mobile"),
                                result.getString("jobRole")
                        ));

            }
            tblEmployee.setItems(data);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }
    }


    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINDASHBOARD,employeeReportContext);
    }

    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOM,employeeReportContext);
    }

    public void btnMealPlans(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPLAN,employeeReportContext);
    }

    public void btnUsers(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINUSER,employeeReportContext);
    }

    public void btnEmployee(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINEMPLOYEE,employeeReportContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,employeeReportContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,employeeReportContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,employeeReportContext);
    }


    public void btnBanquetHalls(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLS,employeeReportContext);
    }

    public void btnReports(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,employeeReportContext);
    }

    public void btnRoomReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINROOMREPORT,employeeReportContext);
    }

    public void btnHallReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINHALLREPORT,employeeReportContext);
    }

    public void btnMealPackageReport(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMINMEALPACKAGEREPORT,employeeReportContext);
    }


    public void btnPrint(ActionEvent actionEvent) {

        InputStream inputStream = this.getClass().getResourceAsStream("..//report//EmployeeReport.jrxml");
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
