package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class MazeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("maze-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        scene.getRoot().requestFocus();//sets scene to listen for keypress events
        scene.getStylesheets().add(MazeApplication.class.getResource("maze-style.css").toExternalForm());
        MazeController controller = fxmlLoader.getController();
        scene.setOnKeyPressed(controller::handleKey);

        stage.setTitle("Maze");
        stage.setScene(scene);
        stage.show();

        /**car drawing
         */
        /** im going to keep it commented for now because not sure if it will stay here
        //body of the car
        Polygon body = new Polygon();
        body.getPoints().addAll(
                50.0, 150.0,
                100.0, 100.0,
                250.0, 100.0,
                300.0, 150.0,
                400.0, 150.0,
                450.0, 200.0,
                0.0, 200.0
        );
        body.setFill(Color.PURPLE);

        // Windows (Rectangles)
        Rectangle frontWindow = new Rectangle(110, 110, 40, 40);
        frontWindow.setFill(Color.GREEN);
        Rectangle rearWindow = new Rectangle(180, 110, 70, 40);
        rearWindow.setFill(Color.GREEN);

        // Wheels
        Circle frontWheel = new Circle(100, 200, 30);
        frontWheel.setFill(Color.BLACK);
        Circle rearWheel = new Circle(350, 200, 30);
        rearWheel.setFill(Color.BLACK);

        // Grouping all elements
        Group car = new Group(body, frontWindow, rearWindow, frontWheel, rearWheel);



    */
    }

    public static void main(String[] args) {
        launch();
    }
}