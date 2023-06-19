package lk.RoyalGatesHotels.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.RoyalGatesHotels.to.Room;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomsModel {
        public static String getLastAdminRoomId() throws SQLException, ClassNotFoundException {
            ResultSet result = CrudUtil.execute("SELECT * FROM room ORDER BY roomNumber DESC LIMIT 1");
            if(result.next()){
                return result.getString("roomNumber");
            }
            return null;

        }

    public static boolean addRoom(Room room) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO room VALUES (?,?,?,?)",
                room.getRoomNumber(),
                room.getRoomType(),
                room.getStatus(),
                room.getPrice()
        );
        return isAdd;

    }

    public static boolean updateRoom(Room room) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE room SET room_type=?, status=?, price=?, WHERE roomNumber=?",
                room.getRoomType(),
                room.getStatus(),
                room.getPrice(),
                room.getRoomNumber()
        );
        return isUpdate;
    }

    public static ResultSet searchRoom(String roomNumber) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM room WHERE roomNumber=?",roomNumber);
        return result;
    }

    public static ResultSet getRoomData() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM room");
        return result;
    }

    public static boolean updateRoomAvailability(String room_number, String unavailable) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE room SET status=? WHERE roomNumber=?",unavailable, room_number);
        return isUpdate;
    }

    public static ObservableList<String> loadRoomNumbers() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = CrudUtil.execute("SELECT roomNumber FROM room");

            ObservableList<String> options = FXCollections.observableArrayList();
            while (resultSet.next()){
                options.add(resultSet.getString("roomNumber"));
            }
            return options;
    }

    public static int getRoomCount() throws SQLException, ClassNotFoundException {
            ResultSet result = CrudUtil.execute("SELECT * FROM room");
            int count=0;
            while (result.next()){
                count++;
            }
            return count;
    }

    public static int getBookedRoomsCount() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM room WHERE status=?","Unavailable");
        int count=0;
        while (result.next()){
            count++;
        }
        return count;
    }

    public static int getAvailableRoomsCount() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM room WHERE status=?","Available");
        int count=0;
        while (result.next()){
            count++;
        }
        return count;
    }

    public static double getRoomPrice(String roomNo) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM room WHERE roomNumber=?", roomNo);
            if(resultSet.next()){
                return Double.parseDouble(resultSet.getString("price"));

            }
            return 0;
    }
}

