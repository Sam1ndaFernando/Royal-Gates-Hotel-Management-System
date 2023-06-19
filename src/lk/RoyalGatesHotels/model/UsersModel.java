package lk.RoyalGatesHotels.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.RoyalGatesHotels.to.Users;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersModel{
    public static boolean addUsers(Users users) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO user VALUES (?,?,?,?,?)",
                users.getEmpId(),
                users.getName(),
                users.getJobRole(),
                users.getUsername(),
                users.getPassword()

        );
        return isAdd;
    }

    public static boolean updateUsers(Users users) throws SQLException, ClassNotFoundException {

        boolean isUpdate = CrudUtil.execute("UPDATE user SET username=?, password=? WHERE EmployeeId=?",
                users.getUsername(),
                users.getPassword(),
                users.getEmpId()
                );
        System.out.println("is update : "+isUpdate);
        return isUpdate;
    }

    public static ResultSet searchEmployee(String employee_id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM user WHERE EmployeeId=?",employee_id);
        return result;
    }


    public static ObservableList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT * FROM employee");
        ObservableList<String> ids = FXCollections.observableArrayList();
        while(result.next()){
            ids.add(result.getString("EmployeeId"));
        }

        return ids;
    }
}
