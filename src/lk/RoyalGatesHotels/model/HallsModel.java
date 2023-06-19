package lk.RoyalGatesHotels.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.RoyalGatesHotels.to.Hall;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HallsModel {
        public static String getLastAdminHallId() throws SQLException, ClassNotFoundException {
            ResultSet result = CrudUtil.execute("SELECT * FROM hall ORDER BY hallNumber DESC LIMIT 1");
            if(result.next()){
                return result.getString("hallNumber");
            }
            return null;

        }

    public static boolean addHall(Hall hall) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO hall VALUES (?,?,?,?)",
                hall.getHallType(),
                hall.getHallNumber(),
                hall.getStatus(),
                hall.getPrice()
        );
        return isAdd;
    }

    public static boolean updateHall(Hall hall) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE hall SET hall_type=?, status=?, price=? WHERE hallNumber=?",
                hall.getHallType(),
                hall.getStatus(),
                hall.getPrice(),
                hall.getHallNumber()
        );
        return isUpdate;
    }

    public static ResultSet searchHall(String hallNumber) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM hall WHERE hallNumber=?",hallNumber);
        return result;
    }

    public static ResultSet getHallData() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM hall");
        return result;
    }

    public static boolean updateHallAvailability(String hall_number) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE hall SET status=? WHERE hallNumber=?","Unavailable", hall_number);
        return isUpdate;
    }

    public static ObservableList<String> loadHallNumbers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT hallNumber FROM hall");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (resultSet.next()){
            options.add(resultSet.getString("hallNumber"));
        }
        return options;
    }

    public static int getBookedHallsCount() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM hall WHERE status=?","Unavailable");
        int count=0;
        while (result.next()){
            count++;
        }
        return count;
    }

    public static int getAvailableHallsCount() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM hall WHERE status=?","Available");
        int count=0;
        while (result.next()){
            count++;
        }
        return count;
    }

    public static double getHallPrice(String hallNo) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM hall WHERE hallNumber=?", hallNo);
        if(resultSet.next()){
            return Double.parseDouble(resultSet.getString("price"));

        }
        return 0;
    }
}

