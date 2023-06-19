package lk.RoyalGatesHotels.to;

public class Guest {

    private String customer_id;
    private String customer_name;
    private String date;
    private String nic;
    private String address;
    private String mobile;
    private String email;
    private String province;


    public Guest(String customer_id, String customer_name, String date, String nic, String address, String mobile, String email, String province) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.date = date;
        this.nic = nic;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.province = province;
    }

    public Guest() {
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "customer_id='" + customer_id + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", date='" + date + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
