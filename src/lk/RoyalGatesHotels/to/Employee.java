package lk.RoyalGatesHotels.to;

public class Employee {
    private String employeeId;
    private String name;
    private String address;
    private String join_date;
    private String nic;
    private String Email;
    private String mobile;
    private String jobRole;

    public Employee(String employeeId, String name, String address, String join_date, String nic, String email, String mobile, String jobRole) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        this.join_date = join_date;
        this.nic = nic;
        Email = email;
        this.mobile = mobile;
        this.jobRole=jobRole;
    }
    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", join_date='" + join_date + '\'' +
                ", nic='" + nic + '\'' +
                ", Email='" + Email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }
}
