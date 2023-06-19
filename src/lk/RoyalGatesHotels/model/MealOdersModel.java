package lk.RoyalGatesHotels.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.RoyalGatesHotels.to.MealOders;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealOdersModel {
        public static String getLastMealOrdersId() throws SQLException, ClassNotFoundException {
            ResultSet result = CrudUtil.execute("SELECT * FROM meal_oders ORDER BY oder_id DESC LIMIT 1");
            if(result.next()){
                return result.getString("oder_id");
            }
            return null;
        }

    public static boolean addOders(MealOders mealOders) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO meal_oders VALUES (?,?,?,?,?)",
                mealOders.getOrderId(),
                mealOders.getCustomerId(),
                mealOders.getDate(),
                mealOders.getQty(),
                mealOders.getPkgId()
        );
        return isAdd;
    }

    public static ResultSet searchOrder(String order) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM meal_oders WHERE oder_id=?",order);
        return result;
    }

    public static boolean updateOrder(MealOders mealOders) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE meal_oders SET customer_id=?, date=?, qty=?, pkg_id=? WHERE oder_id=?",
                mealOders.getCustomerId(),
                mealOders.getDate(),
                mealOders.getQty(),
                mealOders.getPkgId(),
                mealOders.getOrderId()
        );
        return isUpdate;
    }

    public static ObservableList<String> loadOrderIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT oder_id FROM meal_oders");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (resultSet.next()){
            options.add(resultSet.getString("oder_id"));
        }
        return options;
    }
}
