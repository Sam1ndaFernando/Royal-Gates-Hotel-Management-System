package lk.RoyalGatesHotels.tableModel;

public class HallReservationDetailTM {
    private String hallNUmber;
    private String customerId;
    private String reservationId;
    private String checkOutDate;
    private String checkInDate;


    public HallReservationDetailTM() {
    }

    public HallReservationDetailTM(String hallNUmber, String customerId, String reservationId, String checkOutDate, String checkInDate) {
        this.hallNUmber = hallNUmber;
        this.customerId = customerId;
        this.reservationId = reservationId;
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
    }

    public String getHallNUmber() {
        return hallNUmber;
    }

    public void setHallNUmber(String hallNUmber) {
        this.hallNUmber = hallNUmber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
