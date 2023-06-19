package lk.RoyalGatesHotels.tableModel;

public class RoomTM {
    private String roomNumber;
    private String roomType;
    private String status;
    private String price;


    public RoomTM() {
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomTM(String roomNumber, String roomType, String status, String price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
        this.price = price;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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
