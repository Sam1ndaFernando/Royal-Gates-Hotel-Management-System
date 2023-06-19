package lk.RoyalGatesHotels.controller;

import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.RoyalGatesHotels.model.EmployeeModel;
import lk.RoyalGatesHotels.model.GuestModel;
import lk.RoyalGatesHotels.to.Employee;
import lk.RoyalGatesHotels.to.Guest;
import lk.RoyalGatesHotels.util.DateTime;
import lk.RoyalGatesHotels.util.Navigation;
import lk.RoyalGatesHotels.util.RegExPattern;
import lk.RoyalGatesHotels.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReceptionistGuestController implements Initializable {
    public AnchorPane guestReportContext;
    public Label lblTime;
    public Label lblDate;
    public JFXTextField txtMobile;
    public JFXDatePicker DatepickerDate;
    public JFXTextField txtNIC;
    public JFXTextField txtEmail;
    public JFXTextField txtGuestName;
    public JFXTextField txtProvince;
    public JFXTextField txtAddress;
    public JFXTextField txtGuestID;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Pulse(guestReportContext).play();

        lblDate.setText(DateTime.setDate(String.valueOf(LocalDate.now())));
        DateTime.localTime(lblTime);

        setGuestId();
    }

    private void setGuestId() {
        try {
            String lastGuestId= GuestModel.getLastGuestId();
            if(lastGuestId==null){
                txtGuestID.setText("G0001");
            }else{
                String[] split=lastGuestId.split("[G]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newGuestId=String.format("G%04d", lastDigits);
                txtGuestID.setText(newGuestId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e+"").show();
        }

    }

    public void btnDashboard(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONDASHBOARD,guestReportContext);
    }

    public void btnComplaint(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONCOMPLAIN,guestReportContext);
    }

    public void btnMarkMaintenance(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMARKMAINTANCE,guestReportContext);
    }

    public void btnSelectMeals(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONMEALODERS,guestReportContext);
    }

    public void btnReservation(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONRESERVATION,guestReportContext);
    }

    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGINPAGE,guestReportContext);
    }

    public void btnAboutUs(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ABOUTUS,guestReportContext);
    }

    public void btnContact(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CONTACT,guestReportContext);
    }

    public void btnAddGuest(ActionEvent actionEvent) {

        boolean isNameMatched = RegExPattern.getNamePattern().matcher(txtGuestName.getText()).matches();
        boolean isNicMatched = RegExPattern.getIdPattern().matcher(txtNIC.getText()).matches();
        boolean isOldNicMatched = RegExPattern.getOldIDPattern().matcher(txtNIC.getText()).matches();
        boolean isContactNoMatched = RegExPattern.getMobilePattern().matcher(txtMobile.getText()).matches();
        boolean isEmailMatched = RegExPattern.getEmailPattern().matcher(txtEmail.getText()).matches();

        if(isNameMatched) {
            if (isNicMatched || isOldNicMatched) {
                if (isContactNoMatched) {
                    if (isEmailMatched) {

                        Guest guest = new Guest(
                                txtGuestID.getText(),
                                txtGuestName.getText(),
                                String.valueOf(DatepickerDate.getValue()),
                                txtNIC.getText(),
                                txtAddress.getText(),
                                txtMobile.getText(),
                                txtEmail.getText(),
                                txtProvince.getText()
                        );

                        try {
                            boolean isAdd = GuestModel.addGuest(guest);
                            if(isAdd){
                                new Alert(Alert.AlertType.CONFIRMATION,"Guest Added Successfully!").show();
                                Navigation.navigate(Routes.RECEPTIONGUEST,guestReportContext);
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Guest Not Added!").show();
                            }

                        } catch (SQLException | ClassNotFoundException | IOException e) {
                            throw new RuntimeException(e);
                        }

                    }else{txtEmail.requestFocus();}
                }else{txtMobile.requestFocus();}
            }else{txtNIC.requestFocus();}
        }else{txtGuestName.requestFocus();}


    }

    public void btnUpdateGuest(ActionEvent actionEvent) {
        boolean isNameMatched = RegExPattern.getNamePattern().matcher(txtGuestName.getText()).matches();
        boolean isNicMatched = RegExPattern.getIdPattern().matcher(txtNIC.getText()).matches();
        boolean isOldNicMatched = RegExPattern.getOldIDPattern().matcher(txtNIC.getText()).matches();
        boolean isContactNoMatched = RegExPattern.getMobilePattern().matcher(txtMobile.getText()).matches();
        boolean isEmailMatched = RegExPattern.getEmailPattern().matcher(txtEmail.getText()).matches();

        if(isNameMatched){
            if(isNicMatched || isOldNicMatched){
                if(isContactNoMatched){
                    if(isEmailMatched){

                        Guest guest = new Guest(
                                txtGuestID.getText(),
                                txtGuestName.getText(),
                                String.valueOf(DatepickerDate.getValue()),
                                txtNIC.getText(),
                                txtAddress.getText(),
                                txtMobile.getText(),
                                txtEmail.getText(),
                                txtProvince.getText()
                        );

                        try {
                            boolean isUpdate = GuestModel.updateGuest(guest);
                            if(isUpdate){
                                new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!").show();
                                Navigation.navigate(Routes.RECEPTIONGUEST,guestReportContext);
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Employee Not Updated!").show();
                            }
                        } catch (SQLException | ClassNotFoundException | IOException e) {
                            throw new RuntimeException(e);
                        }

                    }else{txtEmail.requestFocus();}
                }else{txtMobile.requestFocus();}
            }else{txtNIC.requestFocus();}
        }else{txtGuestName.requestFocus();}

    }

    public void btnGuests(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,guestReportContext);
    }

    public void btnPayment(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONPAYMENT,guestReportContext);
    }


    public void btnCancelGuest(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONGUEST,guestReportContext);
    }


    public void txtGuestIDOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result = GuestModel.searchGuest(txtGuestID.getText());
            if(result.next()){
                txtGuestName.setText(result.getString("customer_name"));
                txtNIC.setText(result.getString("nic"));
                txtAddress.setText(result.getString("address"));
                txtMobile.setText(result.getString("Mobile"));
                txtEmail.setText(result.getString("Email"));
                txtProvince.setText(result.getString("Province"));
                DatepickerDate.setValue(LocalDate.parse(result.getString("date")));

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
