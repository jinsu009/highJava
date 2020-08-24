package gugudan;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GugudanEvent extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(GugudanEvent.class.getResource("gugudanEvent.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("구구단");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
