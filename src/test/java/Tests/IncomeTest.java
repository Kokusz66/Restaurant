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
    int result2= 2000;
    int result3 = 800;
    int fakeResult = 1001;
    int totalIncome = 0;

    ObservableList<Food> foods = FXCollections.observableArrayList();
    @Before
    public void init() {
        foods.add(new Food("Valami", 1000, "Ft"));
        foods.add(new Food("Valami2", 2000, "Ft"));
        foods.add(new Food("Valami2", 800, "Ft"));
    }

    @Test
    public void incomeTest(){

        int a;
        a = foods.get(0).getFoodPrice();
        totalIncome = totalIncome + a;
        assertEquals(result, totalIncome);
        assertNotEquals(fakeResult, totalIncome);
    }
    @Test
    public void incomeTest2(){
        int a;
        a=foods.get(1).getFoodPrice();
        totalIncome= totalIncome + a;
        assertEquals(result2,totalIncome);
        assertNotEquals(fakeResult, totalIncome);
    }
    @Test
    public void incomeTest3(){
        int a;
        a=foods.get(2).getFoodPrice();
        totalIncome= totalIncome + a;
        assertEquals(result3,totalIncome);
        assertNotEquals(fakeResult, totalIncome);
    }
    @After
    public void delete(){
        foods.removeAll();
    }
}
