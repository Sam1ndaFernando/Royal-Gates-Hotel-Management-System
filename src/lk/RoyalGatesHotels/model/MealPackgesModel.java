package lk.RoyalGatesHotels.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.RoyalGatesHotels.to.MealPackges;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealPackgesModel {

    public static String getLastMealPkgId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM mealpackages ORDER BY pkg_id DESC LIMIT 1");
        if (result.next()) {
            return result.getString("pkg_id");
        }
        return null;
    }


    public static boolean addPackage(MealPackges mealPackges) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO mealpackages VALUES (?,?,?,?,?)",
                mealPackges.getPkg_id(),
                mealPackges.getPrice(),
                mealPackges.getDescription(),
                mealPackges.getMeal_plan(),
                mealPackges.getType()
        );
        return isAdd;
    }

    public static boolean updatePackage(MealPackges mealPackges) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE mealpackages SET price=?, description=?, meal_plan=?, type=? WHERE pkg_id=?",
                mealPackges.getPrice(),
                mealPackges.getDescription(),
                mealPackges.getMeal_plan(),
                mealPackges.getType(),
                mealPackges.getPkg_id()
        );
        return isUpdate;
    }

    public static ResultSet searchMealPlan(String pkg_id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM mealpackages WHERE pkg_id=?", pkg_id);
        return result;
    }

    public static ResultSet getMealPackgeData() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM mealpackages");
        return result;
    }

    public static ObservableList<String> loadPackageIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT pkg_id FROM mealpackages");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (resultSet.next()) {
            options.add(resultSet.getString("pkg_id"));
        }
        return options;
    }


    public static double getPackagePrice(String pkg_id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT price FROM mealpackages WHERE pkg_id=?", pkg_id);
        if (resultSet.next()) {
            return resultSet.getDouble("price");
        }
        return 0;
    }

    public static double searchMealPackgeData(String pkg_id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT price FROM mealpackages WHERE pkg_id=?", pkg_id);
        if (resultSet.next()) {
            return resultSet.getDouble("price");
        }
        return 0;
    }
}

