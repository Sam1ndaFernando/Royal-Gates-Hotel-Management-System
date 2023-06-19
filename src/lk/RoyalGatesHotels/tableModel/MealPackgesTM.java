package lk.RoyalGatesHotels.tableModel;

public class MealPackgesTM {
    private String pkg_id;
    private String price;
    private String description;
    private String meal_plan;
    private String type;

    public MealPackgesTM() {
    }
    public MealPackgesTM(String pkg_id, String price, String description, String meal_plan, String type) {
        this.pkg_id = pkg_id;
        this.price = price;
        this.description = description;
        this.meal_plan = meal_plan;
        this.type = type;
    }

    public String getPkg_id() {
        return pkg_id;
    }

    public void setPkg_id(String pkg_id) {
        this.pkg_id = pkg_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeal_plan() {
        return meal_plan;
    }

    public void setMeal_plan(String meal_plan) {
        this.meal_plan = meal_plan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
