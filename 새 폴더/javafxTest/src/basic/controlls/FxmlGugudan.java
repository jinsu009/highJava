package basic.controlls;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlGugudan extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(FxmlGugudan.class.getResource("fxmlGugudan.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("구구단");
			primaryStage.show();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
