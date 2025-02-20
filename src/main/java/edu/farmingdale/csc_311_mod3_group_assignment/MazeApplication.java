package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
    }

    

    public static void main(String[] args) {
        launch();
    }
}