package lk.RoyalGatesHotels.to;

public class Complain {
    private String roomNumber;
    private String hallNumber;
    private String ComplainId;
    private String CustomerId;
    private String date;
    private String time;
    private String description;

    public Complain(String roomNumber, String hallNumber, String complainId, String customerId, String date, String time, String description) {
        this.roomNumber = roomNumber;
        this.hallNumber = hallNumber;
        ComplainId = complainId;
        CustomerId = customerId;
        this.date = date;
        this.time = time;
        this.description = description;
    }
    public Complain() {
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }

    public String getComplainId() {
        return ComplainId;
    }

    public void setComplainId(String complainId) {
        ComplainId = complainId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Complain{" +
                "roomNumber='" + roomNumber + '\'' +
                ", hallNumber='" + hallNumber + '\'' +
                ", ComplainId='" + ComplainId + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
