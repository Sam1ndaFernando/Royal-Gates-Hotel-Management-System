package lk.RoyalGatesHotels.model;

import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.SQLException;

public class HallAvailabilityModel {

    public static boolean updateHallAvailability(String hall_number) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE hall_availability SET status=? WHERE hall_number=?", "Unavailable", hall_number);
        return isUpdate;
    }

}