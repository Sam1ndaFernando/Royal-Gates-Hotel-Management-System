package lk.RoyalGatesHotels.to;

public class Maintenance {

    private String maintenanceId;
    private String number;
    private String date;
    private String startTime;
    private String endTime;

    public Maintenance() {
    }

    public Maintenance(String maintenanceId, String number, String date, String startTime, String endTime) {
        this.maintenanceId = maintenanceId;
        this.number = number;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(String maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
