package lk.RoyalGatesHotels.model;

import lk.RoyalGatesHotels.to.Complain;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplainModel {
    public static String getLastComplainId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM complain ORDER BY complaintId DESC LIMIT 1");
        if(result.next()){
            return result.getString("complaintId");
        }
        return null;
    }

    public static boolean addComplain(Complain complain) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO complain VALUES (?,?,?,?,?,?,?)",
                complain.getRoomNumber(),
                complain.getHallNumber(),
                complain.getComplainId(),
                complain.getCustomerId(),
                complain.getDate(),
                complain.getTime(),
                complain.getDescription()
        );
        return isAdd;
    }

    public static boolean updateComplain(Complain complain) throws SQLException, ClassNotFoundException {

        boolean isUpdate = CrudUtil.execute("UPDATE complain SET roomNumber=?, hall_number=?, customer_id=?, date=?, time=?, description=? WHERE complaintId=?",
                complain.getRoomNumber(),
                complain.getHallNumber(),
                complain.getCustomerId(),
                complain.getDate(),
                complain.getTime(),
                complain.getDescription(),
                complain.getComplainId()

        );
        return isUpdate;
    }

    public static ResultSet searchComplain(String ComplainId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM complain WHERE complaintId=?",ComplainId);
        return result;
    }

    public static int getComplaintCount() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM complain");
        int count=0;
        while (result.next()){
            count++;
        }
        return count;
    }
}
