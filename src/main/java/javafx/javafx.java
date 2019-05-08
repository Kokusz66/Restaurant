package javafx;

import Guest.Guest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileWriter;

public class javafx extends Application {

    Button nextSceneButton, orderButton;
    Stage window;
    Scene scene1, scene2, orderScene;
    TableView<Food> table;
    //Scene root_scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setTitle("Restaurant");

        //Buttons
        nextSceneButton = new Button("Go for the menu:");
        nextSceneButton.setOnAction(e -> {
            Guest.returnNumberOfGuest();
            window.setScene(scene2);
            System.out.println("Changing to scene2");
        });

        orderButton = new Button("Order it!");
        orderButton.setOnAction(e ->
            window.setScene(orderScene));

        //Table

        TableColumn<Food, String> foodNameColumn = new TableColumn<>("Name");
        foodNameColumn.setMaxWidth(500);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));

        TableColumn<Food, String> foodPriceColumn = new TableColumn<>("Price");
        foodPriceColumn.setMaxWidth(500);
        foodPriceColumn.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));

        table = new TableView<>();
        table.setItems(BasicFoods());
        table.getColumns().addAll(foodNameColumn,foodPriceColumn);



        //Label

        Label welcome = new Label("Welcome to the Restaurant Application!");
        welcome.setFont(new Font(20));

        //set layouts

        VBox layout1 = new VBox();
        layout1.setAlignment(Pos.TOP_CENTER);
        layout1.setSpacing(30);
        layout1.setPadding(new Insets(20));
        layout1.getChildren().addAll(welcome,nextSceneButton);

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(table, orderButton);
       // layout2.setAlignment(Pos.BOTTOM_LEFT);
        layout2.setPadding(new Insets(20));



        scene1 = new Scene(layout1,500, 300);
        scene2 = new Scene(layout2,500,300);
        //orderScene = new Scene();


        //root_scene = new Scene(root);
        window.setScene(scene1);
        window.show();
    }
    public ObservableList<Food> BasicFoods() throws Exception{

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ObservableList<Food> food = FXCollections.observableArrayList();
        food.add(new Food("Slice Chicken", 1000, "Ft"));
        food.add(new Food("Ordinary Pizza", 900, "Ft"));
        food.add(new Food("Spaghetti", 800, "Ft"));
        food.add(new Food("Meal", 1000, "Ft"));

        food.add(new Food("Soda", 200, "Ft"));
        food.add(new Food("Coca-cola", 250, "Ft"));
        food.add(new Food("Sprite", 250, "Ft"));
        food.add(new Food("Water", 250, "Ft"));


        FileWriter fileWriter = new FileWriter("Menu.json");
        gson.toJson(food, fileWriter);
        fileWriter.close();

        //Food[] foods = gson.fromJson(new FileReader("Menu.json"), Food[].class);
        //System.out.println(foods);

        return food;
    }

    /*public  orderButtonClicked(){



    }*/


}





