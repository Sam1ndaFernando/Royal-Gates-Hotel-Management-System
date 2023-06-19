package lk.RoyalGatesHotels.to;

public class Users {
    private String empId;
    private String name;
    private String jobRole;
    private String username;
    private String password;

    public Users(String empId, String name, String jobRole, String username, String password) {
        this.empId = empId;
        this.name = name;
        this.jobRole = jobRole;
        this.username = username;
        this.password = password;
    }

    public Users(){

    }
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Users{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
