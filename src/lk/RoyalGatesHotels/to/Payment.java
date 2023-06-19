package lk.RoyalGatesHotels.to;

public class Payment{
    private String paymentId;
    private String hallReservationId;
    private String roomReservationId;
    private String time;
    private String date;
    private String OrderId;
    private String customerId;
    private double amount;
    private Object reservationId;
    private Object qty;

    public Payment() {
    }

    public Payment(String paymentId, String hallReservationId, String time, String date, String orderId, String customerId, String roomReservationId, double amount) {
        this.paymentId = paymentId;
        this.hallReservationId = hallReservationId;
        this.roomReservationId = roomReservationId;
        this.time = time;
        this.date = date;
        OrderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getHallReservationId() {
        return hallReservationId;
    }

    public void setHallReservationId(String hallReservationId) {
        this.hallReservationId = hallReservationId;
    }

    public String getRoomReservationId() {
        return roomReservationId;
    }

    public void setRoomReservationId(String roomReservationId) {
        this.roomReservationId = roomReservationId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public Object getReservationId() {

        return reservationId;
    }

    public void setReservationId(Object reservationId) {
        this.reservationId = reservationId;
    }

    public Object getQty() {

        return qty;
    }

    public void setQty(Object qty) {
        this.qty = qty;
    }
}