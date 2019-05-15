package main;

import AlertBox.Alert;
import food.Food;
import Guest.Guest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileReader;
import java.io.FileWriter;


public class Javafx extends Application {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    int totalIncome = 0;

    Button menuButton, orderButton, backToMainPage,backToMainPage2,suggestButton, endButton, leaveButton, niceButton;
    Stage window;
    Scene scene1, scene2, scene3, sceneBye;
    TableView<Food> table;
    TextField foodInput, priceInput;


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Restaurant");

        //Buttons
        menuButton = new Button("Go for the menu:");
        menuButton.setOnAction(e -> {
            Guest.addGuest();
            window.setScene(scene2);
            logger.info("Button pressed, going to the menu scene");
            //System.out.println("Changing to menuScene");
        });

        orderButton = new Button("Order it!");
        orderButton.setOnAction(e -> {
            selectOrder();



        });

        backToMainPage = new Button("Back to the Main page");
        backToMainPage.setOnAction(e -> {
            logger.info("Button pressed, going to the main page");
            window.setScene(scene1);
        });

        backToMainPage2 = new Button("Back to the Main page");
        backToMainPage2.setOnAction(e -> {
            logger.info("Button pressed, going to the main page");
            window.setScene(scene1);
        });

        suggestButton = new Button("Suggest a new food!");
        suggestButton.setOnAction(e -> {
            try {
                suggestButtonClicked();
                logger.info("The suggestion is successful");
            } catch (Exception e1) {
                Alert.display("Warning", "Not a real number! Please try again!");
                logger.info("Alert is done!");
            }
        });

        leaveButton = new Button("Leave without order");
        leaveButton.setOnAction(e -> {
            logger.info("Button pressed, nothing ordered");
            window.setScene(sceneBye);

        });

        endButton = new Button("End of the day!");
        endButton.setAlignment(Pos.BOTTOM_LEFT);
        endButton.setOnAction(e -> {
            window.close();
            logger.info("\n\nEnd of the day, number of guest: {} and the total income is: {} Ft\n\n", Guest.returnNumberOfGuest(), totalIncome);
        });

        //Table

        TableColumn<Food, String> foodNameColumn = new TableColumn<>("Name");
        foodNameColumn.setMaxWidth(500);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));

        TableColumn<Food, Integer> foodPriceColumn = new TableColumn<>("Price");
        foodPriceColumn.setMaxWidth(500);
        foodPriceColumn.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));

        TableColumn<Food, String> foodCurrencyColumn = new TableColumn<>("Currency");
        foodCurrencyColumn.setCellValueFactory(new PropertyValueFactory<>("sign"));

        table = new TableView<>();
        table.setItems(BasicFoods());
        table.getColumns().addAll(foodNameColumn,foodPriceColumn, foodCurrencyColumn);


        //User input
        foodInput = new TextField();
        foodInput.setPromptText("Food Name");
        foodInput.setMinWidth(100);

        priceInput = new TextField();
        priceInput.setPromptText("Price");
        priceInput.setMinWidth(100);


        //Label

        Label welcome = new Label("Welcome to the Restaurant Application!");
        welcome.setFont(new Font(20));

        Label orderedLabel = new Label("Thank your orders!");
        orderedLabel.setFont(new Font(20));

        Label byeLabel = new Label("Thanks for your visiting");
        byeLabel.setFont(new Font(20));

        //set layouts

        VBox layout1 = new VBox();
        layout1.setAlignment(Pos.TOP_CENTER);
        layout1.setSpacing(30);
        layout1.setPadding(new Insets(20));
        layout1.getChildren().addAll(welcome,menuButton, endButton);

        HBox suggestLayout = new HBox();
        suggestLayout.setPadding(new Insets(10,10,10,10));
        suggestLayout.setSpacing(10);
        suggestLayout.getChildren().addAll(foodInput,priceInput, suggestButton);

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(table, orderButton, leaveButton, suggestLayout);
        layout2.setPadding(new Insets(20));

        VBox layout3 = new VBox();
        layout3.getChildren().addAll(orderedLabel, backToMainPage);
        layout3.setPadding(new Insets(20));

        HBox orderNothing = new HBox();
        orderNothing.getChildren().addAll(byeLabel);
        byeLabel.setFont(new Font(36));


        VBox byeScene = new VBox(150);
        byeScene.setPadding(new Insets(30,30,30,30));
        byeScene.getChildren().addAll(orderNothing,backToMainPage2);
        orderNothing.setAlignment(Pos.TOP_CENTER);
        backToMainPage2.setAlignment(Pos.CENTER);


        //Scenes

        scene1 = new Scene(layout1,600, 350);
        scene2 = new Scene(layout2,600,350);
        scene3 = new Scene(layout3, 600, 350);
        sceneBye = new Scene(byeScene,600, 350);


        window.setScene(scene1);
        window.show();
    }

    /**
     * Az alap fogyasztható {@code Food} típusú ételek inicializálása és
     * ennek megfelelő Json állomány létrehozása.
     *
     * @return Visszaadja a létrehozott ételek {@code ObservableList<Food>} típusú listájáz
     * ami bekerül az alkalmazás során megjelenített táblázatba.
     */
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
        logger.info("Json created");
        gson.toJson(food, fileWriter);
        fileWriter.close();

        return food;
    }

    /**
     * Vendég által {@code foodInput.getText()} beírt {@link Food} típusú
     * étel beillesztése a táblázatba illetve ennek megfelelő Json állomány
     * létrehozása.
     */
    public void suggestButtonClicked() throws Exception{
        Food newFood = new Food();
        newFood.setFoodName(foodInput.getText());
        newFood.setFoodPrice(Integer.parseInt(priceInput.getText()));
        table.getItems().add(newFood);
        foodInput.clear();
        priceInput.clear();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter("SuggestedFoods.json");
        gson.toJson(newFood, fileWriter);
        fileWriter.close();

        Food result = gson.fromJson(new FileReader("SuggestedFoods.json"), Food.class);
        logger.info("New food suggested!: " + result);
    }

    /**
     * Annak vizsgálata, hogy a vendék választott-e valamit a menüről
     * vagy sem.
     */
    public void selectOrder(){

        ObservableList<Food> foodSelected;
        foodSelected = table.getSelectionModel().getSelectedItems();

        if (foodSelected.isEmpty()){
            Alert.display("Warning", "Please select something or press the 'Leave' button!");
            logger.info("Alert is done");

        } else{
            RaiseIncome();
            window.setScene(scene3);
            logger.info("Order is done, moving to the end scene");
        }
    }


    /**
     * Összbevétel növelése a táblában kiválasztott
     * étel árával.
     */

    public int RaiseIncome() {
        int a;
        a = table.getSelectionModel().getSelectedItem().getFoodPrice();
        totalIncome = totalIncome + a;

        return totalIncome;
    }


}







