package Tests;

import food.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class IncomeTest {

    int result = 1000;
    int fakeResult = 1001;
    int totalIncome = 0;

    ObservableList<Food> foods = FXCollections.observableArrayList();
    @Before
    public void init() {

        foods.add(new Food("Valami", 1000, "Ft"));
    }

    @Test
    public void incomeTest(){

        int a;
        a = foods.get(0).getFoodPrice();

        totalIncome = totalIncome + a;

        assertEquals(result, totalIncome);
        assertNotEquals(fakeResult, totalIncome);
    }
    @After
    public void delete(){
        foods.removeAll();
    }

}
