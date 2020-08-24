package kr.or.ddit.zip.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlMainZip extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(FxmlMainZip.class.getResource("../fxml/FxmlZip.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("우편번호 검색");
		primaryStage.show(); 
	} 

	public static void main(String[] args) {
		launch(args);
	}
}
