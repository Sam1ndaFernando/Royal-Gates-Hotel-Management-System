package lk.RoyalGatesHotels.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.to.HallReservation;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HallReservationModel {


    public static String getLastHallReservationId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM hallReservationDetail ORDER BY reservationId DESC LIMIT 1");
        if(result.next()){
            return result.getString("reservationId");
        }
        return null;

    }

    public static boolean addReservation(HallReservation hallReservation) throws SQLException, ClassNotFoundException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdd = CrudUtil.execute("INSERT INTO hallReservationDetail VALUES (?,?,?,?,?)",
                    hallReservation.getHall_number(),
                    hallReservation.getCustomer_id(),
                    hallReservation.getReservation_id(),
                    hallReservation.getCheck_out_date(),
                    hallReservation.getCheck_in_date()
            );
            System.out.println("hallReservationDetail : "+isAdd);

            if (isAdd){
                boolean isSetAvailability = HallsModel.updateHallAvailability(hallReservation.getHall_number());

                System.out.println("updateHallAvailability : "+isSetAvailability);
                if(isSetAvailability){
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally{
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    public static ObservableList<String> loadReservationIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM hallreservationdetail");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (resultSet.next()){
            options.add(resultSet.getString("reservation_id"));
        }
        return options;
    }


    public static String getReservationDetails(String reservation_id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM hallReservationDetail WHERE reservation_id=?", reservation_id);
        if(resultSet.next()){
            return resultSet.getString("hallNumber");
        }
        return null;
    }
}
