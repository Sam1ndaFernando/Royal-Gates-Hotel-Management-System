package lk.RoyalGatesHotels.to;

public class Room {
    private int room_number;
    private String roomType;
    private String status;
    private double price;

    public Room(int roomNumber, String roomType, String status, double price) {

        this.room_number = roomNumber;
        this.roomType = roomType;
        this.status = status;
        this.price = price;
    }


    public int getRoomNumber() {
        return room_number;
    }

    public void setRoomNumber(int roomNumber) {
        this.room_number = roomNumber;
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


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + room_number + '\'' +
                ", roomType='" + roomType + '\'' +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
