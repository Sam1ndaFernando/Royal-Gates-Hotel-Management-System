package lk.RoyalGatesHotels.to;

public class HallReservation {
    private String hall_number;
    private String customer_id;
    private String reservation_id;
    private String check_out_date;
    private String check_in_date;

    public HallReservation() {
    }

    public HallReservation(String hall_number, String customer_id, String reservation_id, String check_out_date, String check_in_date) {
        this.hall_number = hall_number;
        this.customer_id = customer_id;
        this.reservation_id = reservation_id;
        this.check_out_date = check_out_date;
        this.check_in_date = check_in_date;
    }

    public String getHall_number() {
        return hall_number;
    }

    public void setHall_number(String hall_number) {
        this.hall_number = hall_number;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(String check_out_date) {
        this.check_out_date = check_out_date;
    }

    public String getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(String check_in_date) {
        this.check_in_date = check_in_date;
    }
}
