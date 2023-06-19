package lk.RoyalGatesHotels.tableModel;

public class RoomReservationDetailTM {
    private String roomNumber;
    private String CustomerId;
    private String reservationId;
    private String checkOutDate;
    private String checkInDate;


    public RoomReservationDetailTM() {
    }

    public RoomReservationDetailTM(String roomNumber, String customerId, String reservationId, String checkOutDate, String checkInDate) {
        this.roomNumber = roomNumber;
        CustomerId = customerId;
        this.reservationId = reservationId;
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }
}