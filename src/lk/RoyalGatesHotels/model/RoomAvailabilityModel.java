package lk.RoyalGatesHotels.model;

import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.SQLException;

public class RoomAvailabilityModel {


    public static boolean addRoomAvailability(String room_number) throws SQLException, ClassNotFoundException {

        boolean isUpdate = CrudUtil.execute("UPDATE room_availability SET status=? WHERE room_id=?","Unavailable",room_number);
        return isUpdate;

    }
}
