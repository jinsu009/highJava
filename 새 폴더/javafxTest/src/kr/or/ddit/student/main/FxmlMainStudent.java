package kr.or.ddit.student.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlMainStudent extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(FxmlMainStudent.class.getResource("../fxml/FxmlStudentMain.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("성적표");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
