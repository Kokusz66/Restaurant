package AlertBox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Alert {
    /**
     * Vendég hibáját jelző felugró ablak implementálása.
     * @param title Tetszőlegesen megadható cím a felugró ablaknak.
     * @param message Tetszőlegesen megadható hiba üzenet.
     */

    public static void display(String title, String message){
        Stage window = new Stage();

        /**
         * {@link window.initModality(Modality.APPLICATION_MODAL);}
         * Beállitja, hogy amikor megjelenik a figyelmeztető ablak, akkor a felhasználó
         * ne tudjon semmilyen más feladatot végrehajtani.
         * Továbbá a felugró ablak címének és a maximum szélességi beállítása.
         */
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMaxWidth(400);

        Label label = new Label();
        label.setText(message);
        label.setFont(new Font(18));
        Button closeButton = new Button("Ok!");
        closeButton.setFont(new Font(10));
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(60);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(50));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }
}
