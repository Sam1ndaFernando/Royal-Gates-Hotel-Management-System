package lk.RoyalGatesHotels.tableModel;

public class EmployeeTM {
    private String employeeId;
    private String name;
    private String nic;
    private String address;
    private String mobile;
    private String email;
    private String joinDate;

    private String jobRole;

    public EmployeeTM() {
    }

    public EmployeeTM(String employeeId, String name, String nic, String address, String mobile, String email, String joinDate, String jobRole) {
        this.employeeId = employeeId;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.joinDate = joinDate;
        this.jobRole = jobRole;
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

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }
}

