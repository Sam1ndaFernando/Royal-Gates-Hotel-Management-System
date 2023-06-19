package lk.RoyalGatesHotels.tableModel;

public class HallTM {
    private String hallType;
    private String hallNumber;
    private String status;
    private String price;

    public HallTM() {
    }
    public HallTM(String hallType, String hallNumber, String status, String price) {
        this.hallType = hallType;
        this.hallNumber = hallNumber;
        this.status = status;
        this.price = price;
    }

    public String getHallType() {
        return hallType;
    }

    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
