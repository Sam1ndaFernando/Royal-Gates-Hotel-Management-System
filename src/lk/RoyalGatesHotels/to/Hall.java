package lk.RoyalGatesHotels.to;

public class Hall {
    private String hallType;
    private String hallNumber;
    private String status;
    private Double price;

    public Hall(String hallType, String hallNumber, String status, Double price) {
        this.hallType = hallType;
        this.hallNumber = hallNumber;
        this.status = status;
        this.price = price;
    }

    public Hall() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "hallType='" + hallType + '\'' +
                ", hallNumber='" + hallNumber + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                '}';
    }
}
