package lk.RoyalGatesHotels.model;

import lk.RoyalGatesHotels.to.Payment;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import lk.RoyalGatesHotels.to.Payment;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import lk.RoyalGatesHotels.to.Payment;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentModel {

    public static String getLastPaymentId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM payment ORDER BY paymentId DESC LIMIT 1");
        if (result.next()) {
            return result.getString("paymentId");
        }
        return null;
    }

    public static boolean addPayment(Payment payment) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO payment VALUES (?,?,?,?,?,?,?,?)",
                payment.getPaymentId(),
                payment.getReservationId(),
                payment.getTime(),
                payment.getDate(),
                payment.getOrderId(),
                payment.getCustomerId(),
                payment.getQty(),
                payment.getRoomReservationId()
        );
        return isAdd;
    }

    public static double getTodayEarnings(String date) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM payment WHERE date=?", date);
        double amount = 0;
        while (resultSet.next()) {
            amount += Double.parseDouble(resultSet.getString("qty"));
        }
        return amount;
    }
}
