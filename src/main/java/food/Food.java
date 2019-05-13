package food;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Food {

    private String  foodName;
    private int foodPrice;
    private String sign;

    public Food(){
        this.foodName="";
        this.foodPrice=0;
        this.sign="Ft";
    }

    public Food(String foodName, int foodPrice, String sign){
        this.foodPrice=foodPrice;
        this.foodName=foodName;
        this.sign=sign;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}

