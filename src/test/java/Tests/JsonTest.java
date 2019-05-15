package Tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import food.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;

public class JsonTest {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ObservableList<Food> food = FXCollections.observableArrayList();

    @Before
    public void init(){
       food.add(new Food("Something",200,"Ft"));
       food.add(new Food("Something3",400,"Ft"));
    }

    @Test
    public void writer() throws Exception{

        FileWriter fileWriter = new FileWriter("TestMenu.json");
        gson.toJson(food, fileWriter);
        fileWriter.close();
    }
    @After
    public void delete(){
        food.removeAll();
    }


}
