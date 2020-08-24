package kr.or.ddit.prod.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlmainProd extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(FxmlmainProd.class.getResource("../fxml/Fxmlprod.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("상품관리");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
